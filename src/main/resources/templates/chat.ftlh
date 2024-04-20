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
</head>
<body>
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
                    <li class="mx-3 nav-item">
                        <a class="nav-link" href="/register">Регистрироваться</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div id="username-page">
    <div class="username-page-container">
        <h1 class="title">Digite seu nome</h1>
        <form id="usernameForm" name="usernameForm">
            <div class="form-group">
                <input type="text" id="name" placeholder="Nome"
                       autocomplete="off" value="${sender.email}" class="form-control"/>
            </div>
            <div class="form-group">
                <button type="submit" class="accent username-submit">Comece a conversar</button>
            </div>
        </form>
    </div>
</div>

<div id="chat-page" class="hidden">
    <div class="chat-container">
        <div class="chat-header">
            <h2>${recipient.name} ${recipient.surname}</h2>
        </div>
        <div class="connecting">Conectando ao chat...</div>
        <ul id="messageArea">

        </ul>
        <form id="messageForm" name="messageForm" nameForm="messageForm">
            <div class="form-group">
                <div class="input-group clearfix">
                    <input type="text" id="message" placeholder="Digite uma mensagem..."
                           autocomplete="off" class="form-control"/>
                    <button type="submit" class="primary">Enviar</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script
        src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/js/chat.js"></script>
</body>
</html>
