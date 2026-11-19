
import java.util.Locale;

public class Chatbot {

    public String getResponse(String message) {

        // NLP: Convert input to lowercase and clean the text
        String input = message.toLowerCase(Locale.ROOT)
                              .replaceAll("[^a-z0-9\\s]", " ")
                              .replaceAll("\\s+", " ")
                              .trim();

        // Empty message
        if (input.isEmpty()) {
            return "Please type something so I can help you.";
        }

        // Greeting
        if (input.matches(".*\\b(hello|hi|hey)\\b.*")) {
            return "Hello! Welcome to Java Chatbot. How can I help you?";
        }

        // Chatbot name
        else if (input.contains("your name")) {
            return "I am Java Chatbot, your virtual assistant!";
        }

        // Creator
        else if (input.contains("who created you")
                || input.contains("who made you")) {
            return "I was created using Java programming language.";
        }

        // How are you
        else if (input.contains("how are you")) {
            return "I'm doing great! Thanks for asking.";
        }

        // Internship FAQ
        else if (input.contains("internship")) {
            return "An internship is a training program that gives "
                 + "students practical work experience and helps "
                 + "them develop professional skills.";
        }

        // Java FAQ
        else if (input.contains("java")) {
            return "Java is a popular, object-oriented programming "
                 + "language used to build desktop, web and mobile applications.";
        }

        // Artificial Intelligence FAQ
        else if (input.contains("artificial intelligence")
                || input.equals("what is ai")
                || input.equals("define ai")
                || input.equals("tell me about ai")) {
            return "Artificial Intelligence (AI) is technology that "
                 + "enables computers to perform tasks that normally "
                 + "require human intelligence.";
        }

        // Natural Language Processing FAQ
        else if (input.contains("nlp")
                || input.contains("natural language processing")) {
            return "NLP stands for Natural Language Processing. "
                 + "It helps computers understand and process human language.";
        }

        // Machine Learning FAQ
        else if (input.contains("machine learning")) {
            return "Machine Learning is a branch of AI that allows "
                 + "computers to learn patterns from data.";
        }

        // Chatbot FAQ
        else if (input.contains("chatbot")) {
            return "A chatbot is a software application that "
                 + "communicates with users through text or voice.";
        }

        // Project information
        else if (input.contains("your project")
                || input.contains("what can you do")
                || input.equals("help")) {
            return "I can answer questions about Java, AI, NLP, "
                 + "machine learning, internships and general greetings.";
        }

        // Thank you
        else if (input.contains("thank")) {
            return "You're welcome! Happy to help.";
        }

        // Goodbye
        else if (input.matches(".*\\b(bye|goodbye)\\b.*")) {
            return "Goodbye! Have a wonderful day!";
        }

        // Default response
        else {
            return "Sorry, I don't know the answer to that yet. "
                 + "Please try asking about Java, AI, NLP or internships.";
        }
    }
}
