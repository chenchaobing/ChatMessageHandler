package org.sythe.suf.message;

import org.sythe.suf.message.command.ICommandManager;
import org.sythe.suf.message.command.InvalidArugmentException;
import org.sythe.suf.message.command.InvalidNameException;
import org.sythe.suf.message.command.MissingArgumentsException;

/**
 * @author Jacob A. Leach
 * 
 */
public abstract class AbstractCommandMessageHandler extends AbstractMessageHandler
{
	private String commandString;
	private ICommandManager commandManager;

	public AbstractCommandMessageHandler(String commandString, ICommandManager commandManager)
	{
		this.commandString = commandString;
		this.commandManager = commandManager;
	}

	/**
	 * @param message
	 * @throws InvalidNameException
	 */
	@Override
	public final void handleMessage(ITextMessage message)
	{
		if (commandString != null && message.getMessageText().startsWith(commandString))
		{
			onCommand(message);
		}
		else
		{
			onMessage(message);
		}
	}

	/**
	 * @param message
	 * @throws InvalidNameException
	 * @throws InvalidArugmentException
	 * @throws MissingArgumentsException
	 */
	private final void onCommand(ITextMessage message)
	{
		commandManager.handleCommand(message.getSender(), message.getMessageText().substring(commandString.length()));
	}

	protected final ICommandManager getCommandManager()
	{
		return commandManager;
	}

	/**
	 * @param message
	 */
	@Override
	public abstract void onMessage(ITextMessage message);
}
