// Navigation and Section Handling
document.getElementById('profileSection').addEventListener('click', function() {
    showSection('profileContent');
});

document.getElementById('upcomingRideSection').addEventListener('click', function() {
    showSection('upcomingRideContent');
});

document.getElementById('completedRideSection').addEventListener('click', function() {
    showSection('completedRideContent');
});

document.getElementById('locationUpdateSection').addEventListener('click', function() {
    showSection('locationUpdateContent');
});

document.getElementById('activeStatusSection').addEventListener('click', function() {
    showSection('activeStatusContent');
});

// Show only the selected section
function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.style.display = 'none';
    });
    document.getElementById(sectionId).style.display = 'block';
}

// Active/Inactive Toggle Button
const toggleStatusBtn = document.getElementById('toggleStatusBtn');
toggleStatusBtn.addEventListener('click', function() {
    if (toggleStatusBtn.textContent === 'Set as Active') {
        toggleStatusBtn.textContent = 'Set as Inactive';
        toggleStatusBtn.classList.add('inactive');
    } else {
        toggleStatusBtn.textContent = 'Set as Active';
        toggleStatusBtn.classList.remove('inactive');
    }
});

// Logout functionality (You can handle actual logout in your backend using servlet)
document.getElementById('logoutBtn').addEventListener('click', function() {
    alert("You have been logged out.");
    window.location.href = 'login.html'; // Redirect to login page after logout
});
