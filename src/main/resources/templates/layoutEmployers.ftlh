<#macro layout>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Employers main page</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="../static/style.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-pV0wnJ7PUQO7abOt+jiM72Of4wrgtOPfck+C5gUnibl00Rpakl8E6c5jIxMFwYkN" crossorigin="anonymous"></script>
    </head>

    <header>
        <nav class="navbar navbar-expand-md bg-dark " data-bs-theme="dark">
            <div class="container">
                <a class="navbar-brand" href="#"><img class="logo" src="/static/images/logo.png" alt="Job search"></a>
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
                        <li class="mx-3 nav-item">
                            <a class="nav-link " href="#">сотрудники</a>
                        </li>
                        <li class="mx-3 nav-item">
                            <a class="nav-link" href="#">Журнал</a>
                        </li>
                        <li class="mx-3 nav-item">
                            <a class="nav-link" href="#">Взять книгу</a>
                        </li>
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

    <footer class="bg-dark py-5">
        <div class="container footer-container ">
            <div class=" opacity-75 mb-2 ">
                <a href="#" class="text-decoration-none text-light">Соглашение пользователя </a>
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