package dev.voras.common.zos3270.spi;

import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dev.voras.common.zos3270.AttentionIdentification;
import dev.voras.common.zos3270.FieldNotFoundException;
import dev.voras.common.zos3270.ITerminal;
import dev.voras.common.zos3270.KeyboardLockedException;
import dev.voras.common.zos3270.TextNotFoundException;
import dev.voras.common.zos3270.TimeoutException;
import dev.voras.common.zos3270.internal.comms.Network;
import dev.voras.common.zos3270.internal.comms.NetworkThread;

public class Terminal implements ITerminal {

    private final Screen screen;
    private final Network network;
    private NetworkThread networkThread;

    private int defaultWaitTime = 1_200_000;

    private Log logger = LogFactory.getLog(getClass());

    public Terminal(String host, int port) {
        network = new Network(host, port);
        screen = new Screen(80, 24, network);
    }

    public synchronized void connect() throws NetworkException {
        network.connectClient();
        networkThread = new NetworkThread(screen, network, network.getInputStream());
        networkThread.start();
    }

    public void disconnect() {
        network.close();
        try {
            networkThread.join();
        } catch (InterruptedException e) { //NOSONAR
            logger.error("Problem joining network thread",e);
        }
        networkThread = null;
    }


    @Override
    public ITerminal waitForKeyboard() throws TimeoutException, KeyboardLockedException {
        logger.trace("Waiting for keyboard");
        screen.waitForKeyboard(defaultWaitTime);
        logger.trace("Wait for keyboard complete");
        return this;
    }


    public Screen getScreen() {
        return this.screen;
    }

    @Override
    public ITerminal positionCursorToFieldContaining(@NotNull String text) throws TextNotFoundException, KeyboardLockedException {
        screen.positionCursorToFieldContaining(text);
        return this;
    }

    @Override
    public Terminal verifyTextInField(String text) throws TextNotFoundException {
        screen.searchFieldContaining(text);
        return this;
    }

    @Override
    public Terminal waitForTextInField(String text) throws TextNotFoundException {
        screen.waitForTextInField(text, defaultWaitTime);
        return this;
    }

    @Override
    public ITerminal type(String text) throws KeyboardLockedException, FieldNotFoundException {
        screen.type(text);
        return this;
    }

    @Override
    public ITerminal tab() throws KeyboardLockedException, FieldNotFoundException {
        screen.tab();
        return this;
    }

    @Override
    public ITerminal enter() throws KeyboardLockedException, NetworkException {
        network.sendDatastream(screen.aid(AttentionIdentification.ENTER));
        return this;
    }

    @Override
    public ITerminal clear() throws KeyboardLockedException, NetworkException {
        network.sendDatastream(screen.aid(AttentionIdentification.CLEAR));
        return this;
    }

    @Override
    public ITerminal pf3() throws KeyboardLockedException, NetworkException {
        network.sendDatastream(screen.aid(AttentionIdentification.PF3));
        return this;
    }

    @Override
    public ITerminal reportScreen() {
        logger.trace("\n" + screen.printScreen());
        return this;
    }

    @Override
    public ITerminal reportScreenWithCursor() {
        logger.info("\n" + screen.printScreenTextWithCursor());
        return this;
    }

	public String getHostPort() {
		return this.network.getHostPort();
	}


}
