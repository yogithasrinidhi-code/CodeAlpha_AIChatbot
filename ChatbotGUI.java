
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class ChatbotGUI {

    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private Chatbot chatbot;

    public ChatbotGUI() {
        chatbot = new Chatbot();
    }

    public void start() {

        // Main window
        frame = new JFrame("AI Chatbot - Virtual Assistant");
        frame.setSize(750, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 245, 255));
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        // Header
        JLabel title = new JLabel("AI CHATBOT");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Your Virtual Assistant");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(Color.WHITE);

        JPanel headingPanel = new JPanel(new GridLayout(2, 1));
        headingPanel.setBackground(new Color(35, 90, 170));
        headingPanel.setBorder(new EmptyBorder(12, 15, 12, 15));
        headingPanel.add(title);
        headingPanel.add(subtitle);

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 15));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setBackground(Color.WHITE);
        chatArea.setForeground(new Color(35, 45, 65));
        chatArea.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(new LineBorder(new Color(210, 220, 235)));

        // Input field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 15));
        inputField.setBorder(new CompoundBorder(
            new LineBorder(new Color(180, 195, 220)),
            new EmptyBorder(10, 10, 10, 10)
        ));

        // Buttons
        JButton sendButton = new JButton("Send");
        JButton clearButton = new JButton("Clear Chat");
        JButton helpButton = new JButton("Help");

        styleButton(sendButton, new Color(35, 100, 200));
        styleButton(clearButton, new Color(220, 75, 75));
        styleButton(helpButton, new Color(70, 130, 100));

        // Bottom input panel
        JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.setBackground(new Color(240, 245, 255));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Top buttons panel
        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.RIGHT)
        );
        buttonPanel.setBackground(new Color(240, 245, 255));
        buttonPanel.add(helpButton);
        buttonPanel.add(clearButton);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBackground(new Color(240, 245, 255));
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(inputPanel, BorderLayout.CENTER);

        // Add components
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Welcome message
        chatArea.append(
            "Chatbot: Hello! Welcome to Java Chatbot.\n"
        );
        chatArea.append(
            "Chatbot: Type a message to start chatting.\n\n"
        );

        // Button actions
        sendButton.addActionListener(e -> sendMessage());

        inputField.addActionListener(e -> sendMessage());

        clearButton.addActionListener(e -> clearChat());

        helpButton.addActionListener(e -> showHelp());

        // Display window
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Button styling
    private void styleButton(JButton button, Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(9, 15, 9, 15));
        button.setOpaque(true);
    }

    // Send message
    private void sendMessage() {

        String message = inputField.getText().trim();

        if (message.isEmpty()) {
            return;
        }

        chatArea.append("You: " + message + "\n");

        String response = chatbot.getResponse(message);

        chatArea.append("Chatbot: " + response + "\n\n");

        // Save conversation
        saveToHistory("You: " + message);
        saveToHistory("Chatbot: " + response);
        saveToHistory("");

        inputField.setText("");

        // Scroll to latest message
        chatArea.setCaretPosition(
            chatArea.getDocument().getLength()
        );
    }

    // Clear chat
    private void clearChat() {

        int choice = JOptionPane.showConfirmDialog(
            frame,
            "Are you sure you want to clear the chat?",
            "Clear Chat",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            chatArea.setText(
                "Chatbot: Hello! Welcome to Java Chatbot.\n\n"
            );
        }
    }

    // Help section
    private void showHelp() {

        String helpMessage =
            "WELCOME TO JAVA CHATBOT!\n\n"
            + "You can ask questions about:\n"
            + "1. Java Programming\n"
            + "2. Artificial Intelligence\n"
            + "3. Natural Language Processing\n"
            + "4. Machine Learning\n"
            + "5. Internships\n"
            + "6. Chatbots\n\n"
            + "Example questions:\n"
            + "What is Java?\n"
            + "What is AI?\n"
            + "What is NLP?\n"
            + "What is an internship?\n"
            + "How are you?";

        JOptionPane.showMessageDialog(
            frame,
            helpMessage,
            "Chatbot Help",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Save chat history to a text file
    private void saveToHistory(String text) {

        try (FileWriter writer =
                 new FileWriter("chat_history.txt", true)) {

            writer.write(text + System.lineSeparator());

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                frame,
                "Unable to save chat history.",
                "File Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
