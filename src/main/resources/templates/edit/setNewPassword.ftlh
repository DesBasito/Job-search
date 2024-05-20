<#import "../layout.ftlh" as main>
<@main.layout;spring>
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
        <h1><@spring.message 'change.password'/></h1>
        <main>
            <#if _csrf??>
            <#if accType == 'employee'>
            <form id="password-form" action="/applicant/changePassword" method="post">
                <#else>
                <form id="password-form" action="/employer/changePassword" method="post">
                    </#if>
                    <input type="password" class="form-control" id="current-password" placeholder="<@spring.message 'change.old.password'/>"
                           name="oldPassword" required>
                    <input type="password" class="form-control" id="newPassword" placeholder="<@spring.message 'change.new.password'/>"
                           name="newPassword" required>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                    <button type="submit">Change Password</button>
                    <p class="error-message" id="error-message"></p>
                </form>
                </#if>
        </main>
    </div>
</@main.layout>
