<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Avatar</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding-top: 50px;
            background-color: #f8f9fa; /* Light gray background */
        }

        h1 {
            color: #333; /* Dark text color */
        }

        #avatar-img {
            border-radius: 50%;
            width: 150px;
            height: 150px;
            object-fit: cover;
            cursor: pointer; /* Change cursor on hover */
            transition: transform 0.2s ease-in-out; /* Smooth scaling effect */
        }

        #avatar-img:hover {
            transform: scale(1.1); /* Scale up on hover */
        }

        #avatar-input {
            display: none;
        }

        label {
            display: block;
            margin-top: 20px;
            color: #007bff; /* Blue color for the label */
            cursor: pointer; /* Change cursor on hover */
            transition: color 0.2s ease-in-out;
        }

        label:hover {
            color: #0056b3; /* Darker blue on hover */
        }

        small {
            display: block;
            margin-top: 5px;
            color: #666; /* Gray color for the small text */
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Change Your Avatar</h1>
    <main>
        <form id="avatar-form" action="http://localhost:9785/applicant/uploadImage" method="post" enctype="multipart/form-data">
            <fieldset>
                <img id="avatar-img" src="default-avatar.jpg" alt="Current Avatar">
                <div class="mb-3">
                    <label for="avatar-input" class="avatar-input-label">set image</label>
                    <input name="file" class="avatar-input" type="file" id="avatar-input">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="current-email" placeholder="Current Email" name="email"
                           required>
                </div>
                <br>
                <button type="submit"> change ava</button>
            </fieldset>
        </form>
    </main>
</div>

<script>
    document.getElementById('avatar-input').addEventListener('change', function () {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('avatar-img').src = e.target.result;
            }
            reader.readAsDataURL(file);
        }
    });
</script>
</body>
</html>