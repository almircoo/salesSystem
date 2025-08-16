/**
 * 
 */

document.addEventListener('DOMContentLoaded', function() {
    const sidebarMenu = document.getElementById('sidebar-menu');

    if (sidebarMenu) {
        const currentPath = window.location.pathname.split('/').pop();
        const navLinks = sidebarMenu.querySelectorAll('a[data-page]');

        navLinks.forEach(link => {
            const page = link.getAttribute('data-page');

            if (currentPath.includes(page)) {
                // Elimina la clase 'active' de todos los elementos
                navLinks.forEach(innerLink => innerLink.closest('li').classList.remove('active'));
                
                // Agrega la clase 'active' al elemento actual
                link.closest('li').classList.add('active');
            }
        });
    }
});