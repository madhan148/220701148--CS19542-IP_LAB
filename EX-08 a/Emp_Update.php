<?php
$errors = [];
$name = $email = $phone = $department = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Validate Name
    if (empty($_POST["name"])) {
        $errors['name'] = "Name is required";
    } else {
        $name = trim($_POST["name"]);
        if (!preg_match("/^[a-zA-Z ]*$/", $name)) {
            $errors['name'] = "Only letters and white space allowed";
        }
    }

    // Validate Email
    if (empty($_POST["email"])) {
        $errors['email'] = "Email is required";
    } else {
        $email = trim($_POST["email"]);
        if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $errors['email'] = "Invalid email format";
        }
    }

    // Validate Phone
    if (empty($_POST["phone"])) {
        $errors['phone'] = "Phone number is required";
    } else {
        $phone = trim($_POST["phone"]);
        if (!preg_match("/^[0-9]{10}$/", $phone)) {
            $errors['phone'] = "Invalid phone number format";
        }
    }

    // Validate Department
    if (empty($_POST["department"])) {
        $errors['department'] = "Department is required";
    } else {
        $department = trim($_POST["department"]);
    }

    // If no errors, update the database (pseudo code)
    if (empty($errors)) {
        // Update logic here
        echo "<div class='success'>Employee details updated successfully!</div>";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Update - Variation 2</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        form {
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            width: 300px;
        }
        input[type="text"], input[type="email"], input[type="tel"], select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            width: 100%;
        }
        .error {
            color: red;
            font-size: 0.9em;
        }
        .success {
            color: green;
            font-size: 1.1em;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h2>Employee Update Form</h2>
    <form method="post" action="">
        <label for="name">Name:</label>
        <input type="text" name="name" value="<?php echo htmlspecialchars($name); ?>">
        <div class="error"><?php echo isset($errors['name']) ? $errors['name'] : ''; ?></div>

        <label for="email">Email:</label>
        <input type="email" name="email" value="<?php echo htmlspecialchars($email); ?>">
        <div class="error"><?php echo isset($errors['email']) ? $errors['email'] : ''; ?></div>

        <label for="phone">Phone Number:</label>
        <input type="tel" name="phone" value="<?php echo htmlspecialchars($phone); ?>">
        <div class="error"><?php echo isset($errors['phone']) ? $errors['phone'] : ''; ?></div>

        <label for="department">Department:</label>
        <select name="department">
            <option value="">Select Department</option>
            <option value="HR" <?php echo ($department == 'HR') ? 'selected' : ''; ?>>HR</option>
            <option value="IT" <?php echo ($department == 'IT') ? 'selected' : ''; ?>>IT</option>
            <option value="Finance" <?php echo ($department == 'Finance') ? 'selected' : ''; ?>>Finance</option>
        </select>
        <div class="error"><?php echo isset($errors['department']) ? $errors['department'] : ''; ?></div>

        <input type="submit" value="Update">
    </form>
</body>
</html>
