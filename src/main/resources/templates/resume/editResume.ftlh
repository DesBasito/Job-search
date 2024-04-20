<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container py-5">
        <div class="row">
            <div class="col-md-4">
                <#if user.avatar??>
                    <img src="/api/users/image/${user.avatar}" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                <#else >
                    <img src="/api/users/image/anon.jpeg" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                </#if>
                <div class="user-info">
                    <h4>${user.name} ${user.surname}</h4>
                    <p class="text-muted"><strong>Age:</strong> ${user.age}</p>
                    <p class="text-muted"><strong>Phone Number:</strong> ${user.phoneNumber}</p>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card border-0 shadow-lg">
                    <main>
                        <form action="/resume/update?id=${resume.id}" method="post">
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
                                    <#list resume.workExpInfoEdit as work>
                                        <div class="mb-5">
                                            <h6>
                                                <label>
                                                    <input name="workExpInfoEdit[${work_index}].id" value="${work.id}"
                                                           placeholder="id" disabled>
                                                </label>
                                                <label>
                                                    <input type="text" name="workExpInfoEdit[${work_index}].position" value="${work.position}"
                                                           placeholder="Position">
                                                </label> - <label>
                                                    <input type="text" name="workExpInfoEdit[${work_index}].companyName"
                                                           value="${work.companyName}"
                                                           placeholder="Company Name">
                                                </label></h6>
                                            <p><label>
                                                    <textarea name="workExpInfoEdit[${work_index}].responsibilities"
                                                              placeholder="Responsibilities">${work.responsibilities}</textarea>
                                                </label>
                                            </p>
                                            <p class="text-muted">Worked: <label>
                                                    <input type="text" name="workExpInfoEdit[${work_index}].years"
                                                           placeholder="Years">
                                                </label> years</p>
                                        </div>
                                    </#list>
                                    <#assign workExpLastIndex = resume.workExpInfoEdit?size - 1>
                                </div>
                                <div id="work">
                                    <div class="d-flex justify-content-center my-4">
                                        <input id="add-work-btn" class="btn btn-success mt-3 mb-1" type="button"
                                               value="Add work experience">
                                    </div>
                                </div>
                                <hr class="my-4"> <!-- Horizontal line -->

                                <h5 class="card-subtitle mb-3 text-muted">Education</h5>
                                <div class="education">
                                    <#list resume.educationInfo as edu>
                                        <div class="mb-3">
                                            <label>
                                                <input name="educationInfo[${edu_index}].id" value="${edu.id}"
                                                       placeholder="id" disabled>
                                            </label>
                                            <h6><label>
                                                    <input type="text" name="educationInfo[${edu_index}].degree" value="${edu.degree}"
                                                           placeholder="Degree">
                                                </label>, <label>
                                                    <input type="text" name="educationInfo[${edu_index}].institution"
                                                           placeholder="Institution">
                                                </label></h6>
                                            <p><label>
<textarea name="educationInfo[${edu_index}].program"
          placeholder="Program">${edu.program}</textarea>
                                                </label></p>
                                            <p class="text-muted"><label>
                                                    <input type="date" name="educationInfo[${edu_index}].startDate"
                                                           value="${edu.startDate}"
                                                           placeholder="Start Date">
                                                </label> - <label>
                                                    <input type="date"
                                                           name="educationInfo[${edu_index}].endDate"
                                                           value="${edu.endDate}"
                                                           placeholder="End Date">
                                                </label>
                                        </div>
                                    </#list>
                                    <#assign eduLastIndex = resume.educationInfo?size-1>
                                </div>
                                <div id="education">
                                    <div id="educationInfo" class="d-flex justify-content-center my-4">
                                        <input id="add-education-btn" class="btn btn-success my-3" type="button" value="Add education info">
                                    </div>
                                </div>

                                <hr class="my-4"> <!-- Horizontal line -->
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
    <script>
        var workExpLastIndex = ${workExpLastIndex};
        var eduLastIndex = ${eduLastIndex};
    </script>
    <script src="/js/editResume.js"></script>

    <link rel="stylesheet" href="/resumeInfo.css">
</@main.layout>
