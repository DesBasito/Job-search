<#import "../layout.ftlh" as main>
<@main.layout>
    <h1 class="mb-4">Create Resume</h1>

    <main>

        <form action="/resume/create" method="POST">
            <!-- Card for Resume Details Section -->
            <div class="card mb-4">
                <div class="card-body">
                    <h5 class="card-title">Resume Details</h5>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="resumeName" class="form-label">Resume Name</label>
                            <input type="text" class="form-control" id="resumeName" name="title" required>
                        </div>
                        <div class="col-md-6">
                            <label for="category" class="form-label">Category:</label>
                            <input type="text" list="datalistOptions" class="form-control" id="category" name="categoryName"
                                   placeholder="name of category">
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
                            <label for="userEmail" class="form-label">Email</label>
                            <input type="email" class="form-control" id="userEmail" name="userEmail" required>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Card for Work Experience Section -->
            <div class="card mb-4">
                <div class="card-body" id="workExp">
                    <h5 class="card-title">Work Experience</h5>
                    <div class="mb-3">
                        <label for="workPosition" class="form-label">Work Position</label>
                        <input type="text" class="form-control" id="workPosition" name="position">
                    </div>
                    <div class="mb-3">
                        <label for="companyName" class="form-label">Company Name</label>
                        <input type="text" class="form-control" id="companyName" name="companyName">
                    </div>
                    <div class="mb-3">
                        <label for="responsibilities" class="form-label">Responsibilities</label>
                        <textarea class="form-control" id="responsibilities" name="responsibilities"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="yearsOfWork" class="form-label">Years of Work</label>
                        <input type="number" class="form-control" id="yearsOfWork" name="years">
                    </div>
                </div>
                <div id="work">
                    <input id="add-work-btn" type="button" value="+">
                </div>
            </div>

            <!-- Card for Education Section -->
            <div class="card mb-4" id="education">
                <div class="card-body">
                    <h5 class="card-title">Education</h5>
                    <div class="mb-3">
                        <label for="degree" class="form-label">Degree</label>
                        <input type="text" class="form-control" id="degree" name="degree">
                    </div>
                    <div class="mb-3">
                        <label for="institution" class="form-label">Institution</label>
                        <input type="text" class="form-control" id="institution" name="institution">
                    </div>
                    <div class="mb-3">
                        <label for="program" class="form-label">Program</label>
                        <textarea class="form-control" id="program" name="program"></textarea>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="startDate" class="form-label">Start Date</label>
                            <input type="date" class="form-control" id="startDate" name="startDate">
                        </div>
                        <div class="col-md-6">
                            <label for="endDate" class="form-label">End Date</label>
                            <input type="date" class="form-control" id="endDate" name="endDate">
                        </div>
                    </div>
                </div>
                <div id="educationInfo">
                    <input id="add-education-btn" type="button" value="+">
                </div>
            </div>

            <div class="card mb-4">
                <div class="card-body">
                    <h5 class="card-title">Contacts info</h5>
                    <div class="mb-3">
                        <label for="instagram" class="form-label">Instagram</label>
                        <input type="text" class="form-control" id="instagram" name="Instagram">
                    </div>
                    <div class="mb-3">
                        <label for="facebook" class="form-label">Facebook</label>
                        <input type="text" class="form-control" id="facebook" name="Facebook">
                    </div>
                    <div class="mb-3">
                        <label for="telegram" class="form-label">Telegram</label>
                        <input type="text" class="form-control" id="telegram" name="Telegram">
                    </div>
                </div>
                <div id="contacts">
                    <input id="add-contacts-btn" type="button" value="+">
                </div>
            </div>

            <!-- Submit Button -->
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary btn-lg">Create</button>
            </div>
        </form>
    </main>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/js/createResume.js"></script>
</@main.layout>

