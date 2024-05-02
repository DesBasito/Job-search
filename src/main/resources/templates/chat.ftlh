<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Main page</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/chat.css"/>
    <style>
        main {
            padding: 20px;
        }

        .chat-container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: scroll;
        }

        .chat-message {
            max-height: 300px;
            background-color: #e9ecef;
            border-radius: 12px;
            padding: 8px 12px;
            margin-bottom: 8px;
            overflow-y: auto;

            max-width: 70%;
        }

        .chat-input {
            display: flex;
            align-items: center;
            border-top: 1px solid #ced4da;
            padding: 10px;
        }

        .chat-input input {
            flex: 1;
            padding: 8px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            margin-right: 8px;
        }

        .chat-input button {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: #ffffff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .chat-input button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<header>
    <nav class="navbar navbar-expand-md bg-dark " data-bs-theme="dark">
        <div class="container">
            <a class="navbar-brand" href="/vacancies"><img src="/api/users/image/logo.png" width="120px"
                                                           alt="Job search"></a>
            <button class="navbar-toggler text-light bg-orange btn-user ms-auto" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav"
                    aria-expanded="false" aria-label="Toggle navigation">
                <i class="bi bi-list-nested"></i>
            </button>
            <button type="button"
                    class="btn btn-link btn-user bg-orange rounded-1 text-light bg-orange order-md-1 ms-3"
                    onclick="window.location.href='/profile'"
                    data-bs-toggle="tooltip" data-bs-placement="left" title="Profile">
                <i class="bi bi-person-circle"></i>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav fw-medium mx-auto">
                    <li class="nav-item">
                        <#if sender.accType = 'employee'>
                            <a class="nav-link " href="#">Вакансии</a>
                        </#if>
                        <#if sender.accType = 'employer'>
                            <a class="nav-link " href="/resume">Сотрудники</a>
                        </#if>
                    </li>
                    <#if sender.accType = 'employer'>
                        <li class="mx-3 nav-item">
                            <a class="nav-link " href="#">Компании</a>
                        </li>
                    </#if>
                    <li class="mx-3 nav-item">
                        <a class="nav-link" href="/api/users/image/Prime.jpeg">связатся с тех-поддержкой</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

</header>
<body>
<main>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="chat-container">
                    <div class="chatBox py-5" id="chatMessages">
                        <#list  messages as message>
                            <div class="chat-message">${message.senderEmail}: ${message.content}</div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
        <div class="chat-input">
            <input type="text" id="messageInput" placeholder="Type a message...">
            <button class="btn btn-primary btn-send" type="submit">Send</button>
        </div>
    </div>
</main>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script src="/js/chat.js/">
</script>
<script>
    let lastMessageNum = ${messages?size};
</script>
</body>
</html>