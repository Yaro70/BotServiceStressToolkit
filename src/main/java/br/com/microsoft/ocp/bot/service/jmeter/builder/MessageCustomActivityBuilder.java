package br.com.microsoft.ocp.bot.service.jmeter.builder;

import br.com.microsoft.ocp.bot.service.jmeter.plugin.schemas.Conversation;
import br.com.microsoft.ocp.bot.service.jmeter.plugin.schemas.Member;
import br.com.microsoft.ocp.bot.service.jmeter.plugin.schemas.Message;
import br.com.microsoft.ocp.bot.service.jmeter.plugin.schemas.MessageCustom;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import javax.json.JsonObject;

public class MessageCustomActivityBuilder extends ActivityBaseBuilder {

	public MessageCustomActivityBuilder() {
	}

	public static MessageCustom build(String conversationId, JsonObject value, String text, String textFormat, String locale,
									  Member from, Member recipient, String channelId, String serviceUrl) {

		MessageCustom message = new MessageCustom();
		message.setType(Message.MESSAGE_TYPE);
		message.setFrom(from);
		message.setLocale(locale);
		message.setTextFormat(textFormat);
		message.setText(text);
		message.setValue(value);

		message.setTimestamp(
				ISODateTimeFormat.dateHourMinuteSecondMillis().withZoneUTC().print(System.currentTimeMillis()));
		message.setTimestamp(ISODateTimeFormat.dateHourMinuteSecondMillis().withZone(DateTimeZone.forOffsetHours(-3))
				.print(System.currentTimeMillis()));

		message.setRecipient(recipient);
		message.setConversation(new Conversation(conversationId));
		message.setServiceUrl(serviceUrl);
		message.setChannelId(channelId);
		
		setActivityProperties(message);

		return message;
	}
}
