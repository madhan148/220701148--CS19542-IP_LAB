import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register2")
public class RegisterServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/UserDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        if (username == null || username.length() < 5 || username.length() > 15) {
            errorMessage.append("Username must be between 5 and 15 characters.<br>");
            isValid = false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email == null || !email.matches(emailRegex)) {
            errorMessage.append("Invalid email format.<br>");
            isValid = false;
        }

        String passwordRegex = "^(?=.[a-z])(?=.[A-Z]).{8,}$";
        if (password == null || !password.matches(passwordRegex)) {
            errorMessage.append("Password must be at least 8 characters, including uppercase and lowercase.<br>");
            isValid = false;
        }

        if (isValid) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password);

                int rowCount = stmt.executeUpdate();
                if (rowCount > 0) {
                    out.println("<div class='success-message'>Registration successful!</div>");
                } else {
                    out.println("<div class='error'>Registration failed. Please try again.</div>");
                }

                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<div class='error'>Database error: " + e.getMessage() + "</div>");
            }
        } else {
            out.println("<div class='error'>" + errorMessage.toString() + "</div>");
        }

        out.println("<a href='register2.html'>Go Back</a>");
    }
}
