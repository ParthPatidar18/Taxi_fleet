function showSection(sectionId) {
    // Hide all sections
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.classList.add('hidden');
    });

    // Show the selected section
    const selectedSection = document.getElementById(sectionId);
    if (selectedSection) {
        selectedSection.classList.remove('hidden');
    }
}

function logout() {
    // Redirect to logout (implement backend logic in Servlet)
    window.location.href = "logout"; // This should point to your servlet URL for logout
}
