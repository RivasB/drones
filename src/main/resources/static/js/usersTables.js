// Call the dataTables jQuery plugin
$(document).ready(function() {
  usersLoadFromAPI();
  $('#usersTable').DataTable();
});

async function usersLoadFromAPI() {
  const response = await fetch('users/',{
    method: 'GET',
    headers: {
      'Accept':'application/json',
      'Content-Type':'application/json'
    }
  });
  const users = await response.json();
  let userListHTML = '';
  users.forEach(user => {
    let userRoleList = '';
    let enabled = (user.enabled==false) ? 'No':'Sí';
    user.roles.forEach(role => {
      userRoleList += '<div>'+role+'</div>';
    });
    let viewHTML = '<div class="text-center"> <a href="#" onclick="viewUser(\''+ user.username +'\');" class="btn btn-circle btn-primary"></><i class="fas fa-eye"></i></a>'
    let editHTML = '<a> </a> <a href="#" class="btn btn-circle btn-success"><i class="fas fa-edit"></i></a>'
    let deleteHTML = '<a> </a> <a href="#" onclick="deleteUser(\''+ user.username +'\');" class="btn btn-circle btn-danger"><i class="fas fa-user-times"></i></a></div>'
    let userHTML = '<tr><td>'+ user.fullName 
                    +'</td> <td>'+ user.email 
                    +'</td> <td>'+ user.organization 
                    +'</td> <td>'+ user.username 
                    +'</td> <td>'+ userRoleList 
                    +'</td> <td>'+ enabled 
                    +'</td> <td>' + viewHTML + editHTML + deleteHTML +'</td></tr>';
    userListHTML += userHTML;
  });
  document.querySelector('#usersTable tbody').outerHTML = userListHTML;
}

async function deleteUser(username) {
  const mySwal = Swal.mixin({
    confirmButtonColor: '#3085d6',
    confirmButtonText: 'Ok',
    buttonsStyling: true
  });
  if (username=='admin') {
    mySwal.fire({
      title: 'Prohibido',
      text: 'El usuario: "'+username+'" es un usuario protegido del sistema',
      icon: 'error'
    })
    return
  }
  mySwal.fire({
    title: '¿Estás seguro?',
    text: 'Eliminar al usuario "'+username+'" es una acción irreversible',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: '¡Sí, eliminalo!',
    cancelButtonText: '¡No, cancela!',
    cancelButtonColor: '#d33',
    reverseButtons: true
  }).then((result) => {
    if (result.isConfirmed) {
      const request =  fetch('users/delete/'+username,{
        method: 'DELETE',
        headers: {
          'Accept':'application/json',
          'Content-Type':'application/json'
        }}).then(
        mySwal.fire({
        title: '¡Eliminado!',
        text: 'El usuario: "'+username+'" ha sido eliminado.',
        icon: 'success',
      }).then((result)=>{
        if (result.isConfirmed) {
          location.reload();
        }
      }));
    } else if (
      result.dismiss === Swal.DismissReason.cancel
    ) {
      mySwal.fire({
        title: 'Cancelado',
        text: 'El usuario: "'+username+'" está a salvo :)',
        icon: 'error'
      })
    }
  });  
}

async function viewUser(username){
  const response = await fetch('users/'+username,{
    method: 'GET',
    headers: {
      'Accept':'application/json',
      'Content-Type':'application/json'
    }
  });
  const user = await response.json();
  let userRoleList = '';
  user.roles.forEach(role => {
    userRoleList += '<div>'+role+'</div>';
  });
  let enabled = (user.enabled==false) ? 'No':'Sí';
  let userCard = '<div class="card user-card-full">'
  +'<div class="row m-l-0 m-r-0">'
  +    '<div class="col-sm-4 bg-c-lite-green user-profile">'
  +        '<div class="card-block text-center text-white">'
  +            '<div class="m-b-25">'
  +                '<img src="img/undraw_profile.svg" class="img-radius" alt="User-Profile-Image">'
  +            '</div>'
  +            '<h6 class="f-w-600">'+user.fullName+'</h6>'
  +            '<p>'+userRoleList+'</p>'
  +            '<i class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>'
  +        '</div>'
  +    '</div>'
  +    '<div class="col-sm-8">'
  +        '<div class="card-block">'
  +           '<h6 class="m-b-20 p-b-5 b-b-default f-w-600">Información</h6>'
  +            '<div class="row">'
  +                '<div class="col-sm-6">'
  +                    '<p class="m-b-10 f-w-600">Email</p>'
  +                    '<h6 class="text-muted f-w-400">'+user.email+'</h6>'
  +                '</div>'
  +                '<div class="col-sm-6">'
  +                   '<p class="m-b-10 f-w-600">Usuario</p>'
  +                    '<h6 class="text-muted f-w-400">'+user.username+'</h6>'
  +                '</div>'
  +            '</div>'
  +            '<div class="row">'
  +                '<div class="col-sm-6">'
  +                    '<p class="m-b-10 f-w-600">Creado</p>'
  +                    '<h6 class="text-muted f-w-400">'+user.created+'</h6>'
  +                '</div>'
  +                '<div class="col-sm-6">'
  +                   '<p class="m-b-10 f-w-600">Habilitado</p>'
  +                    '<h6 class="text-muted f-w-400">'+enabled+'</h6>'
  +                '</div>'
  +            '</div>'
  +            '<h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600">Organización</h6>'
  +            '<div class="row">'
  +                '<div class="col-sm-6">'
  +                    '<p class="m-b-10 f-w-600">Nombre</p>'
  +                    '<h6 class="text-muted f-w-400">'+user.organization+'</h6>'
  +               '</div>'
  +                '<div class="col-sm-6">'
  +                    '<p class="m-b-10 f-w-600">Roles</p>'
  +                    '<h6 class="text-muted f-w-400">'+userRoleList+'</h6>'
  +                '</div>'
  +            '</div>'
  +            '<ul class="social-link list-unstyled m-t-40 m-b-10">'
  +                '<li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="facebook" data-abc="true"><i class="mdi mdi-facebook feather icon-facebook facebook" aria-hidden="true"></i></a></li>'
  +                '<li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="twitter" data-abc="true"><i class="mdi mdi-twitter feather icon-twitter twitter" aria-hidden="true"></i></a></li>'
  +                '<li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="instagram" data-abc="true"><i class="mdi mdi-instagram feather icon-instagram instagram" aria-hidden="true"></i></a></li>'
  +            '</ul>'
  +        '</div>'
  +    '</div>'
  +'</div>'
+'</div>';
  Swal.fire({
    title: 'Detalles de usuario',
    html: userCard,
    width: 900,
    showCloseButton: true,
    showConfirmButton: false,
    focusClose: false
  })
}