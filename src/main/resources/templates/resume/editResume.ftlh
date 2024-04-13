<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container py-5">
        <div class="row">
            <div class="col-md-4">
                <img src="/api/users/image/${user.avatar}" class="img-fluid rounded-circle shadow-lg mb-3"
                     alt="Applicant image">
                <div class="user-info">
                    <h4>${user.name} ${user.surname}</h4>
                    <p class="text-muted"><strong>Age:</strong> ${user.age}</p>
                    <p class="text-muted"><strong>Phone Number:</strong> ${user.phoneNumber}</p>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card border-0 shadow-lg">
                    <main>
                        <form action="resume/update" method="post">
                            <div class="card-body">
                                <h3 class="card-title mb-4">
                                    <label>
                                        <input type="text" class="form-control" name="title" value="${resume.title}"
                                               placeholder="Resume Name">
                                    </label></h3>
                                <p class="card-text text-muted">
                                    <strong>Category:</strong>
                                    <label>
                                        <input type="text"
                                               name="categoryName"
                                               list="datalistOptions"
                                               value="${resume.categoryName}"
                                               placeholder="Category">
                                    </label>
                                    <datalist id="datalistOptions">
                                        <#list categories as category>
                                        <option value=${category.name}>
                                            </#list>
                                    </datalist>
                                </p>
                                <p class="card-text text-muted"><strong>Salary:</strong> <label>
                                        <input type="text"
                                               name="salary"
                                               value="${resume.salary}"
                                               placeholder="Salary">
                                    </label>
                                </p>

                                <hr class="my-4"> <!-- Horizontal line -->

                                <h5 class="card-subtitle mb-3 text-muted">Work Experience</h5>
                                <div class="work-experience">
                                    <#list works as work>
                                        <div class="mb-5">
                                            <h6><label>
                                                    <input type="text" name="position" value="${work.position}"
                                                           placeholder="Position">
                                                </label> - <label>
                                                    <input type="text" name="companyName"
                                                           value="${work.companyName}"
                                                           placeholder="Company Name">
                                                </label></h6>
                                            <p><label>
                                                    <textarea name="responsibilities"
                                                              value="${work.responsibilities}"
                                                              placeholder="Responsibilities"></textarea>
                                                </label>
                                            </p>
                                            <p class="text-muted">Worked: <label>
                                                    <input type="text" name="years"
                                                           placeholder="Years">
                                                </label> years</p>
                                        </div>
                                    </#list>
                                </div>
                                <div id="work" class="d-flex justify-content-center my-4">
                                    <input id="add-work-btn" class="btn btn-success my-3" type="button"
                                           value="Add work experience">
                                </div>
                                <hr class="my-4"> <!-- Horizontal line -->

                                <h5 class="card-subtitle mb-3 text-muted">Education</h5>
                                <div class="education">
                                    <#list educations as edu>
                                        <div class="mb-3">
                                            <h6><label>
                                                    <input type="text" name="degree" value="${edu.degree}"
                                                           placeholder="Degree">
                                                </label>, <label>
                                                    <input type="text" name="institution"
                                                           placeholder="Institution">
                                                </label></h6>
                                            <p><label>
<textarea name="program" value="${edu.program}"
          placeholder="Program"></textarea>
                                                </label></p>
                                            <p class="text-muted"><label>
                                                    <input type="text" name="startDate"
                                                           value="${edu.startDate}"
                                                           placeholder="Start Date">
                                                </label> - <label>
                                                    <input type="text"
                                                           name="endDate"
                                                           value="${edu.endDate}"
                                                           placeholder="End Date">
                                                </label>
                                            </p>
                                        </div>
                                    </#list>
                                </div>

                                <hr class="my-4"> <!-- Horizontal line -->

                                <h5 class="card-subtitle mb-3 text-muted">Contacts</h5>
                                <div class="work-experience">
                                    <#list contacts as contact>
                                        <div class="mb-3">
                                            <h6><label>
                                                    <input type="text" name="contactType" value="${contact.type}"
                                                           placeholder="Contact Type">
                                                </label> : <label>
                                                    <input type="text" name="infoValue"
                                                           value="${contact.infoValue}"
                                                           placeholder="Info Value">
                                                </label>
                                            </h6>
                                        </div>
                                    </#list>
                                </div>
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary btn-md">Edit</button>
                                </div>
                            </div>
                        </form>
                    </main>

                </div>
            </div>
        </div>
    </div>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/js/editResume.js"></script>

    <link rel="stylesheet" href="/resumeInfo.css">
</@main.layout>
