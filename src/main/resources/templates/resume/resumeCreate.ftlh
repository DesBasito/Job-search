<#import "../layout.ftlh" as main>
<@main.layout>
    <h1 class="mb-4">Create Resume</h1>

    <main>

        <form action="/resume/create" method="POST">
            <div class="card mb-4">
                <div class="card-body">
                    <h5 class="card-title">Resume Details</h5>
                        <div class="col">
                            <label for="resumeName" class="form-label">Resume Name</label>
                            <input type="text" class="form-control" id="resumeName" name="title" required>
                        </div>
                        <div class="col">
                            <label for="category" class="form-label">Category:</label>
                            <input type="text" list="datalistOptions" class="form-control" id="category"
                                   name="categoryName"
                                   placeholder="name of category">
                            <datalist id="datalistOptions">
                                <#list categories as category>
                                <option value=${category.name}>
                                    </#list>
                            </datalist>
                        </div>
                        <div class="col">
                            <label for="salary" class="form-label">Salary</label>
                            <input type="text" class="form-control" id="salary" name="salary" required>
                        </div>
                    </div>
                </div>

            <div class="card mb-4" id="workExp">
                <h5 class="card-title text-center">Work Experience</h5>
                <div id="work" class="d-flex justify-content-center">
                    <input id="add-work-btn" class="btn btn-success my-3" type="button" value="Add work experience">
                </div>
            </div>


            <div class="card mb-4" id="education">
                    <h5 class="card-title text-center">Education</h5>
                <div id="educationInfo" class="d-flex justify-content-center">
                    <input id="add-education-btn" class="btn btn-success my-3" type="button" value="Add education info">
                </div>
            </div>

            <div class="card mb-4" id="addContacts">
                <div class="card-body">
                    <h5 class="card-title">Contacts info</h5>
                    <div class="mb-3">
                        <label for="instagram" class="form-label">Contact type: </label>
                        <input type="text" value="Instagram" class="form-control" id="instagram" name="contacts[0].type">
                    </div>
                    <div class="mb-3">
                        <label for="instagramValue" class="form-label">Contact Value</label>
                        <input type="text" value="Instagram" class="form-control" id="instagramValue" name="contacts[0].infoValue">
                    </div>
                </div>
                <div id="contacts" class="d-flex justify-content-center">
                    <input id="add-contacts-btn" class="btn btn-success my-3" type="button" value="add more">
                </div>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary btn-lg">Create</button>
            </div>
        </form>
    </main>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/js/createResume.js"></script>
</@main.layout>

