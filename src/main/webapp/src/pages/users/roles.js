const roles = [
  {
    id: "ADMIN",
    label: "Admin"
  },
  {
    id: "EXPERT",
    label: "Expert"
  },
  {
    id: "CLIENT",
    label: "Client"
  }
];

export function updateRoles(oldRoles, role) {
  const index = oldRoles.findIndex(r => r.id === role.id);
  if (index !== -1) {
    return oldRoles
      .slice(0, index)
      .concat(oldRoles.slice(index + 1, oldRoles.length));
  } else {
    return oldRoles.concat([role]);
  }
}

export default roles;
