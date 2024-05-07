<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
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
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<#assign known = SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign authorizedUserEmail = SPRING_SECURITY_CONTEXT.authentication.name>
</#if>
<header>
    <nav class="navbar navbar-expand-md bg-dark " data-bs-theme="dark">
        <div class="container">
            <a class="navbar-brand" href="/"><img src="/api/users/image/logo.png" width="120px"
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
                    <li class="nav-item" id="userAuthority">
                        <a class="nav-link active" aria-current="page" href="/resume">Список резюме
                        </a>
                    </li>

                    <li class="mx-3 nav-item">
                        <a class="nav-link " href="#">Компании</a>
                    </li>
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

<body style=" height: 100vh;">
<div class=" py-5">
    <div class="container">
    <div class="row">
        <div class="col-md-8" >
            <div id="resume-pageList"></div>
            <nav aria-label="Page navigation example" id="resume-pageButtons">
            </nav>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Filter Resumes</h5>
                    <form action="/resume/filter" method="get">
                        <div class="mb-3">
                            <label for="category" class="form-label">Category:</label>
                            <input type="text" list="datalistOptions" class="form-control" id="category" name="category"
                                   placeholder="name of category">
                            <datalist id="datalistOptions">
                                <#list categories as category>
                                <option value=${category.name}>
                                    </#list>
                            </datalist>
                        </div>
                        <button type="submit" class="btn btn-primary">Filter</button>
                    </form>
                </div>
                <div class="card card-body mt-2">
                    <form action="/resume" method="get">
                        <h5 class="card-title">Sort Resumes</h5>
                        <div class="my-3">
                            <select class="form-select" name="filter" aria-label="Default select example">
                                <option value="ascDate">date ascending</option>
                                <option value="descDate">date descending</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Filter</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script>
    const resumeSortingBy = '${filter}'
</script>
<script src="/js/resumesPage.js"></script>
</html>
