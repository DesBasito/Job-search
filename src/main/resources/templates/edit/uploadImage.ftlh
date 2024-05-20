<#import "../layout.ftlh" as main>
<@main.layout;spring>
    <style>
        body {
            text-align: center;
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
    <#assign known = SPRING_SECURITY_CONTEXT??>
    <#if known>
        <#assign authorizedUserEmail = SPRING_SECURITY_CONTEXT.authentication.principal>
    </#if>
<div class="container">
    <h1><@spring.message 'change.image'/></h1>
    <main>
        <#if _csrf??>
        <#if accType = 'employee'>
        <form id="avatar-form" action="/applicant/uploadImage" method="post" enctype="multipart/form-data">

        <#else>
            <form id="avatar-form" action="/employer/uploadImage" method="post" enctype="multipart/form-data">
        </#if>
            <fieldset>
                <img id="avatar-img" src="api/users/image/username/${authorizedUserEmail.username}" alt="Current Avatar">
                <div class="mb-3">
                    <label for="avatar-input" class="avatar-input-label"><@spring.message 'setImage'/></label>
                    <input name="file" class="avatar-input" type="file" id="avatar-input">
                </div>
                <br>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                       value="${(_csrf.token)!'--no-token--'}"/>
                <button type="submit"><@spring.message 'change.image'/></button>
            </fieldset>
        </form>
        </#if>
    </main>
</div>

</@main.layout>