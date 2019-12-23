package br.com.microsoft.ocp.bot.service.jmeter.sampler.gui;

import br.com.microsoft.ocp.bot.service.jmeter.sampler.MessageCustomSampler;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MessageCustomSamplerGui extends AbstractSamplerGui {
	public static final long serialVersionUID = 240L;

	private static final int MAX_RESPONSES_ALLOWED = 99;

	private static final String NUMBER_OF_RESPONSES_EXPECTED_LABEL = "# of Responses Expected:";
	private static final String VALUE_LABEL = "Value (JSON string):";
	private static final String MESSAGE_TEXT_LABEL = "Text:";
	private static final String MESSAGE_TEXT_FORMAT_LABEL = "Text Format:";
	private static final String LOCALE_LABEL = "Locale:";

	private javax.swing.JLabel valueLabel = new JLabel();
	private javax.swing.JScrollPane valueAreaScrollPane = new javax.swing.JScrollPane();
	private JLabel messageTextLabel = new JLabel();
	private JLabel messageTextFormatLabel = new JLabel();
	private JLabel localeLabel = new JLabel();
	private JLabel numOfResponsesExpectedLabel = new JLabel();
	private javax.swing.JScrollPane messageTextAreaScrollPane = new javax.swing.JScrollPane();

	private javax.swing.JTextArea valueTextArea = new JTextArea();
	private JTextArea messageTextTextArea = new JTextArea();
	private JTextField messageTextFormatTextField = new JTextField();
	private JTextField localeTextField = new JTextField();
	private JTextField numOfResponsesExpectedTextField;

	public MessageCustomSamplerGui() {
		init();
	}

	@Override
	public String getStaticLabel() {
		return "Bot Service: Message Custom";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configure(TestElement element) {
		super.configure(element);
		numOfResponsesExpectedTextField
				.setText(String.valueOf(element.getPropertyAsInt(MessageCustomSampler.NUM_OF_EXPECTED_RESPONSES)));
		valueTextArea.setText(element.getPropertyAsString(MessageCustomSampler.VALUE));
		messageTextTextArea.setText(element.getPropertyAsString(MessageCustomSampler.MESSAGE_TEXT));
		messageTextFormatTextField.setText(element.getPropertyAsString(MessageCustomSampler.MESSAGE_TEXT_FORMAT));
		localeTextField.setText(element.getPropertyAsString(MessageCustomSampler.LOCALE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TestElement createTestElement() {
		MessageCustomSampler sampler = new MessageCustomSampler();
		modifyTestElement(sampler);
		return sampler;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void modifyTestElement(TestElement te) {
		te.clear();
		configureTestElement(te);

		te.setProperty(MessageCustomSampler.NUM_OF_EXPECTED_RESPONSES,
				Integer.parseInt(numOfResponsesExpectedTextField.getText()));
		te.setProperty(MessageCustomSampler.VALUE, valueTextArea.getText());
		te.setProperty(MessageCustomSampler.MESSAGE_TEXT, messageTextTextArea.getText());
		te.setProperty(MessageCustomSampler.MESSAGE_TEXT_FORMAT, messageTextFormatTextField.getText());
		te.setProperty(MessageCustomSampler.LOCALE, localeTextField.getText());
	}

	/*
	 * Helper method to set up the GUI screen
	 */
	private void init() { // WARNING: called from ctor so must not be overridden
							// (i.e. must be private or final)
		// Standard setup
		setLayout(new BorderLayout(0, 5));
		setBorder(makeBorder());
		add(makeTitlePanel(), BorderLayout.NORTH); // Add the standard title

		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);

		numOfResponsesExpectedLabel = new JLabel(NUMBER_OF_RESPONSES_EXPECTED_LABEL);

		valueTextArea.setColumns(20);
		valueTextArea.setRows(5);
		valueAreaScrollPane.setViewportView(valueTextArea);

		messageTextTextArea.setColumns(20);
		messageTextTextArea.setRows(5);
		messageTextAreaScrollPane.setViewportView(messageTextTextArea);

		NumberFormat format = new DecimalFormat("#0");
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(MAX_RESPONSES_ALLOWED);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);

		numOfResponsesExpectedTextField = new javax.swing.JFormattedTextField(formatter);
		numOfResponsesExpectedLabel.setText(NUMBER_OF_RESPONSES_EXPECTED_LABEL);
		valueLabel.setText(VALUE_LABEL);
		messageTextLabel.setText(MESSAGE_TEXT_LABEL);
		messageTextFormatLabel.setText(MESSAGE_TEXT_FORMAT_LABEL);
		localeLabel.setText(LOCALE_LABEL);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(valueLabel, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(messageTextLabel, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(messageTextFormatLabel, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(localeLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(numOfResponsesExpectedLabel))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(localeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 244,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(numOfResponsesExpectedTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										244, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(valueAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 585,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(messageTextFormatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 244,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(messageTextAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 585,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(0, 469, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(valueAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGap(30, 30, 30).addComponent(valueLabel)))
				.addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(messageTextAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGap(30, 30, 30).addComponent(messageTextLabel)))
				.addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(messageTextFormatLabel).addComponent(messageTextFormatTextField,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(localeLabel).addComponent(localeTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(numOfResponsesExpectedLabel).addComponent(numOfResponsesExpectedTextField,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap(540, Short.MAX_VALUE)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearGui() {
		super.clearGui();

		numOfResponsesExpectedTextField.setText(String.valueOf(MessageCustomSampler.NUM_OF_EXPECTED_RESPONSES_DEFAULT_VALUE));
		valueTextArea.setText(MessageCustomSampler.VALUE_DEFAULT_VALUE);
		messageTextTextArea.setText(MessageCustomSampler.MESSAGE_TEXT_DEFAULT_VALUE);
		messageTextFormatTextField.setText(MessageCustomSampler.MESSAGE_TEXT_FORMAT_DEFAULT_VALUE);
		localeTextField.setText(MessageCustomSampler.LOCALE_DEFAULT_VALUE);
	}

	@Override
	public String getLabelResource() {
		return null;
	}
}
