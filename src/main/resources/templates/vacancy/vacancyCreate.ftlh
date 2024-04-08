<#import "../layouts/layout.ftlh" as main>
<@main.layout>
    <h1 class="mb-4">Create Resume</h1>

    <main>

        <form action="/vacancies/create" method="POST" enctype="application/x-www-form-urlencoded">
            <!-- Card for Resume Details Section -->
            <div class="card mb-4">
                <div class="card-body">
                    <h5 class="card-title">Vacancy Details</h5>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="vacancyName" class="form-label">Vacancy Name</label>
                            <input type="text" class="form-control" id="vacancyName" name="name" required>
                        </div>
                        <div class="col-md-6">
                            <label for="category" class="form-label">Category:</label>
                            <input type="text" list="datalistOptions" class="form-control" id="category" name="category">
                            <datalist id="datalistOptions">
                                <#list categories as category>
                                <option value=${category.name}>
                                    </#list>
                            </datalist>
                        </div>
                        <div class="col-md-6">
                            <label for="salary" class="form-label">Salary</label>
                            <input type="text" class="form-control" id="salary" name="salary" required>
                        </div>
                        <div class="col-md-6">
                            <label for="description" class="form-label">Description</label>
                            <textarea  class="form-control" id="description" name="description" required></textarea>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label for="expFromId" class="form-label">Experience from</label>
                                <input type="number" class="form-control" id="expFromId" name="expFrom">
                            </div>
                            <div class="col-md-6">
                                <label for="expToId" class="form-label">Experience to</label>
                                <input type="number" class="form-control" id="expToId" name="expTo">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary btn-lg">Create</button>
            </div>
        </form>
    </main>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</@main.layout>

