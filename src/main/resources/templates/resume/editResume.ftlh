<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Resume</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        main {
            width: 100%;
            max-width: 600px;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }

        textarea,
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            resize: vertical; /* Allow vertical resizing of textarea */
        }

        button[type="submit"],
        button {
            display: inline-block;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-align: center;
            background-color: #007bff;
            color: #fff;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .action-buttons {
            display: flex;
            justify-content: space-between;
        }

        @media (max-width: 768px) {
            main {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
<main>
    <h1>Edit Resume</h1>
    <form action="/resume/update?id=${resume.id}" method="post">
        <label for="description">Description:</label>
        <textarea id="description" name="description" required maxlength="245">${resume.name}</textarea>

        <label for="category">Category:</label>
        <input type="text" list="datalistOptions" id="category" name="category" value="${resume.category}" required maxlength="30">
        <datalist id="datalistOptions">
            <#list categories as category>
            <option value="${category.name}">
                </#list>
        </datalist>

        <label for="salary">Salary:</label>
        <input type="text" id="salary" name="salary" pattern="^\d+$" title="Please enter only digits" value="${resume.salary}" required>

        <button type="submit">Update Resume</button>
    </form>

    <div class="action-buttons">
        <button <#--onclick="window.location.href = 'infos/addWorkExperience'"  --> >Add Work Experience</button>
        <button <#--onclick="window.location.href = 'infos/addEducation'"--> >Add Education Info</button>
    </div>
</main>
</body>
</html>
