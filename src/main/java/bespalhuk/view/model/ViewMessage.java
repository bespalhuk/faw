package bespalhuk.view.model;

import com.google.common.base.Strings;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ViewMessage {

	private final MessageContext messageContext;

	private final Severity severity;

	private String text;

	private ViewMessage(MessageContext messageContext, Severity severity) {
		this.messageContext = messageContext;
		this.severity = severity;
	}

	public static ViewMessage error(MessageContext messageContext) {
		checkNotNull(messageContext);
		return new ViewMessage(messageContext, Severity.ERROR);
	}

	public static ViewMessage info(MessageContext messageContext) {
		checkNotNull(messageContext);
		return new ViewMessage(messageContext, Severity.INFO);
	}

	public static void alreadyExists(MessageContext messageContext) {
		checkNotNull(messageContext);
		error(messageContext).text("Already exists.").build();
	}

	public ViewMessage text(String text) {
		this.text = text;
		return this;
	}

	public void build() {
		checkArgument(!Strings.isNullOrEmpty(text));
		messageContext.addMessage(severity.builder().defaultText(text).build());
	}

	private enum Severity {
		INFO {
			@Override
			MessageBuilder builder() {
				return new MessageBuilder().info();
			}
		},
		ERROR {
			@Override
			MessageBuilder builder() {
				return new MessageBuilder().error();
			}
		};

		abstract MessageBuilder builder();
	}

}
