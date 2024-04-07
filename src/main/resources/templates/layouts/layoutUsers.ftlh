<#macro layoutUsers>
    <!doctype html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Applicants main page</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../static/style.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
        <style>
            body {
                font-family: 'Raleway', sans-serif;
                font-weight: 600;
                background: #e0e0e0;

            }

            .bg-orange {
                background-color: #F05A00;
            }

            .container {
                max-width: 1200px;
            }

            .btn-user {
                height: 60px;
                font-size: 30px;
            }

            .btn-user:hover {
                color: rgb(152, 91, 47);
                background-color: #F05A00;
            }

            h1 {
                font-size: 40px;
                font-weight: 600;
            }

            .deWALT p {
                font-weight: 500;
                font-size: 14px;
            }

            .deWALT a {
                padding: 10px 25px;
                border-radius: 3px;
            }

            h2 {
                font-size: 40px;
                font-weight: 600;
            }

            h6 {
                font-weight: 600;
            }

            .card img{
                max-width: 212px;
                height: auto;
                margin: 60px  auto 15px auto;
            }

            .logo {
                width: 110px;
            }

            .bg-orange:hover {
                background-color: #b34500;
            }

            h3 {
                font-size: 32px;
                font-weight: 600;
            }

            .btn-user {
                background: #F05A00;
            }

            .res-btn{
                max-width: 170px;
                height: 85px;
            }

            .col h5 {
                font-weight: 600;
                font-size: 20px;
            }

            .col p {
                font-weight: 500;
            }

            .footer-container {
                font-size: 14px;
                color: #fff;
                display: flex;
                align-items: center;
                flex-wrap: wrap;
            }

            .numbers {
                font-size: 18px;
                font-weight: 500;
                line-height: 0.6rem;
            }

            .links a {
                border-radius: 100%;
            }

        </style>
    </head>

    <header>
        <nav class="navbar navbar-expand-md bg-dark " data-bs-theme="dark">
            <div class="container">
                <a class="navbar-brand" href="/vacancies"><img src="../../static/images/logo.png" alt="Job search"></a>
                <button class="navbar-toggler text-light bg-orange btn-user ms-auto" type="button"
                        data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <i class="bi bi-list-nested"></i>
                </button>
                <button type="button" class="btn btn-link btn-user bg-orange rounded-1 text-light bg-orange order-md-1 ms-3"
                        onclick="window.location.href='/profile'"
                        data-bs-toggle="tooltip" data-bs-placement="left" title="Profile">
                    <i class="bi bi-person-circle"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav fw-medium mx-auto">
                        <#if user.accType == 'employee'>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="/vacancies">Вакансии
                                </a>
                            </li>
                            <li class="mx-3 nav-item">
                                <a class="nav-link " href="#">Компании</a>
                            </li>
                        <#else>
                            <a class="nav-link active" aria-current="page" href="/vacancies">Поиск сотрудников
                            </a>
                            <li class="mx-3 nav-item">
                                <a class="nav-link" href="#">связатся с тех-поддержкой</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <body>
    <div class=" py-5">
        <div class="container">
            <#nested>
        </div>
    </div>




    </body>

    <footer class="py-5" style="background-color: #5d5d5d;">
        <div class="container footer-container text-dark">
            <div class=" opacity-75">
                <a href="#" class="text-decoration-none">Соглашение пользователя </a>
                <p>«Copyright © Название 2023»</p>
            </div>

            <div class="numbers ms-lg-5 mb-2 ms-md-3 ps-3  ">
                <p>+7 495 120-32-14</p>
                <p>+7 495 120-32-15</p>
            </div>
        </div>
    </footer>
    </html>
</#macro>