<#import "../layout.ftlh" as main>
<@main.layout>
<style>
    .password-field {
        width: 300px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    h1 {
        font-size: 24px;
        margin-bottom: 20px;
    }

    input {
        width: 100%;
        padding: 12px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        font-size: 16px;
    }

    button {
        background-color: #4CAF50;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
    }

    button:hover {
        background-color: #45a049;
    }

    .error-message {
        color: red;
        margin-bottom: 15px;
    }
</style>
<div class="password-field">
    <h1>Change Your Password</h1>
    <main>
        <form id="password-form" action="/applicant/changePassword" method="post">
            <input type="password" class="form-control" id="current-password" placeholder="Current Password"
                   name="oldPassword" required>
            <input type="email" class="form-control" id="current-email" placeholder="Current Email" name="email"
                   required>
            <input type="password" class="form-control" id="newPassword" placeholder="New Password"
                   name="newPassword" required>
            <input type="password" class="form-control" id="confirm-password" placeholder="Confirm New Password"
                   name="confirmPassword" required>
            <button type="submit">Change Password</button>
            <p class="error-message" id="error-message"></p>
        </form>
    </main>
</div>

<script>
    document.getElementById('password-form').addEventListener('submit', function (event) {
        event.preventDefault();

        const newPassword = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        if (newPassword !== confirmPassword) {
            document.getElementById('error-message').textContent = "New password and confirm password do not match.";
            return;
        }

        this.reset();
        document.getElementById('error-message').textContent = "";
    });
    </@main.layout>
