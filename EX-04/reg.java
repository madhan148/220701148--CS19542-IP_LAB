<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.regex.Pattern"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <style>
        /* Basic Styling */
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f9;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 1em;
        }

        .form-group {
            margin-bottom: 1em;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1em;
        }

        input:focus {
            border-color: #007bff;
            outline: none;
        }

        .error {
            color: red;
            font-size: 0.9em;
            margin-top: 5px;
        }

        .success-message {
            color: green;
            font-weight: bold;
            text-align: center;
            margin-top: 1em;
        }

        button {
            background-color: #007bff;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 1em;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Register</h2>
        <%
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String errorMessage = "";
            boolean isValid = true;

            if (username != null && email != null && password != null) {
                // Username validation (3-12 characters)
                if (username.length() < 3 || username.length() > 12) {
                    errorMessage += "Username must be between 3 and 12 characters.<br>";
                    isValid = false;
                }

                // Email validation
                String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                if (!Pattern.matches(emailRegex, email)) {
                    errorMessage += "Please enter a valid email address.<br>";
                    isValid = false;
                }

                // Password validation (8 characters, 1 uppercase, 1 lowercase, 1 number)
                String passwordRegex = "^(?=.[0-9])(?=.[a-z])(?=.*[A-Z]).{8,}$";
                if (!Pattern.matches(passwordRegex, password)) {
                    errorMessage += "Password must be at least 8 characters long, with uppercase, lowercase, and a number.<br>";
                    isValid = false;
                }

                // Display success or error message
                if (isValid) {
                    out.println("<div class='success-message'>Registration successful!</div>");
                } else {
                    out.println("<div class='error'>" + errorMessage + "</div>");
                }
            }
        %>

        <form method="POST" action="register.jsp">
            <div class="form-group">
                <label for="username">Username (3-12 characters)</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>

            <div class="form-group">
                <label for="password">Password (Min 8 characters, 1 uppercase, 1 lowercase, 1 number)</label>
                <input type="password" id="password" name="password" required>
            </div>

            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>
