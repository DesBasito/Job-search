const resumeCardElement = document.getElementById('resume-pageList');
const resPagingButtons = document.getElementById('resume-pageButtons');
const urlResumes = `/api/resumes/paging`;
let resumePageNum = 1;
let resumeSorting = resumeSortingBy

window.onload = async() =>{
    await fetchAndRenderResume(urlResumes);
    resPagingButtons.innerHTML = `
    <div class="my-2 text-success">${resumePage}: <span id="resume-pagination-number">${resumePageNum}</span></div>
       <ul class="pagination">
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPageResume(resumePageNum - 1, resumeSorting, 'previous')">&laquo;</li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPageResume(1, resumeSorting, '1')">1</a></li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPageResume(2, resumeSorting, '2')">2</a></li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPageResume(3, resumeSorting, '3')">3</a></li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPageResume(resumePageNum + 1, resumeSorting, 'next')">&raquo;</li>
        </ul>
    `;
}

async function fetchAndRenderResume(url) {
    try {
        const response = await fetch(url);
        const dataResumes = await response.json();

        console.log(dataResumes);

        if (dataResumes && Array.isArray(dataResumes.content)) {
            renderResume(dataResumes.content);
        } else {
            console.error('Invalid data format received from the server');
        }
    } catch (error) {
        console.error('Error fetching or processing data:', error);
    }
}

function renderResume(vacancies) {
    resumeCardElement.innerHTML = '';
    vacancies.forEach(vacancy => {
        const card = createResumeCard(vacancy);
        console.log(vacancy)
        resumeCardElement.innerHTML += card;
    });
}

function createResumeCard(resume) {
    return `
        <div class="card mb-3">
            <div class="card-body">
                <a href="#" class="text-decoration-none text-dark">${resume.userEmail}</a>
                <h3 class="mt-2 mb-5"><a href="resume/${resume.id}" class="text-decoration-none text-bg-dark rounded-2 px-3 py-1">${resume.name}</a></h3>
                <p class="card-text">${salaryResume}: ${resume.salary}</p>
                <p class="text-end">${resume.updateDate}</p>
            </div>
        </div>
    `;
}

async function switchPageResume(pageNumber, sortingBy, pageFunc) {
    const pag =document.getElementById('resume-pagination-number')
    switch (pageFunc) {
        case 'previous':
            resumePageNum = Math.max(1, resumePageNum - 1);
            break;
        case 'next':
            resumePageNum++;
            break;
        default:
            resumePageNum = parseInt(pageFunc);
            break;
    }
    pag.innerHTML = ''
    pag.innerHTML = resumePageNum
    const url = `${urlResumes}?page=${resumePageNum}&filter=${sortingBy}`;
    await fetchAndRenderResume(url);
}
