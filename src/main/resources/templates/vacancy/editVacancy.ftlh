<!DOCTYPE html>
<html>
<head>
    <title>Edit Vacancy</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        form {
            max-width: 500px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        label {
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h1>Edit Vacancy</h1>
<main>
    <form action="/vacancies/update?id=${vacancy.id}" method="post">
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" value="${vacancy.name}" required maxlength="30"><br>

        <label for="description">Description:</label><br>
        <textarea id="description" name="description" required maxlength="245">${vacancy.description}</textarea><br>

        <label for="category">Category:</label><br>
        <input type="text" list="datalistOptions" class="form-control" id="category" name="category" value="${vacancy.category}" required maxlength="30">
        <datalist id="datalistOptions">
            <#list categories as category>
            <option value=${category.name}>
                </#list>
        </datalist>
        <label for="salary">Salary:</label><br>
        <input type="text" id="salary" name="salary" pattern="^\d+$" title="Please enter only digits"
               value="${vacancy.salary}" required><br>

        <label for="expFrom">Minimum Experience:</label><br>
        <input type="number" id="expFrom" name="expFrom" min="0" max="40" value="${vacancy.expFrom}" required><br>

        <label for="expTo">Maximum Experience:</label><br>
        <input type="number" id="expTo" name="expTo" min="0" max="50" value="${vacancy.expTo}" required><br>

        <button type="submit">Update Vacancy</button>
    </form>
</main>
</body>
</html>
