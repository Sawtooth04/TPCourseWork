export default function requireRole(role, authentication) {
    if (authentication != null)
        return authentication.roles.includes(role, 0);
    return false;
}