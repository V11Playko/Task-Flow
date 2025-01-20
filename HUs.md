# Fase 1: Seguridad y Gestión de Usuarios
- **HU001**: Como visitante, quiero registrarme en la plataforma proporcionando mi correo electrónico, nombre y contraseña para poder crear una cuenta.
- **HU002**: Como visitante, quiero iniciar sesión en la plataforma utilizando mi correo y contraseña para acceder a mi cuenta.
- **HU003**: Como usuario registrado, quiero poder cerrar sesión para proteger mi cuenta en dispositivos compartidos.
- **HU004**: Como usuario registrado, quiero recuperar mi contraseña proporcionando mi correo electrónico para poder restablecerla en caso de olvido.
- **HU005**: Como administrador, quiero asignar roles (administrador, cliente) a los usuarios para gestionar permisos en la plataforma.
- **HU006**: Como usuario registrado, quiero poder actualizar mi perfil (nombre, correo, dirección) para mantener mi información actualizada.
- **HU007**: Como administrador, quiero visualizar una lista de usuarios registrados para gestionar su información.

# Fase 2: Catálogo de Productos
- **HU008**: Como administrador, quiero agregar nuevos productos al catálogo proporcionando nombre, descripción, precio, imágenes y stock para que estén disponibles a los clientes.
- **HU009**: Como administrador, quiero editar la información de un producto existente para mantenerla actualizada.
- **HU010**: Como administrador, quiero eliminar productos del catálogo para retirar aquellos que ya no estén disponibles.
- **HU011**: Como cliente, quiero ver una lista de productos disponibles para conocer lo que puedo comprar.
- **HU012**: Como cliente, quiero buscar productos por nombre o categoría para encontrar rápidamente lo que necesito.
- **HU013**: Como cliente, quiero ver los detalles de un producto (nombre, descripción, precio, imágenes y stock disponible) para decidir si quiero comprarlo.

# Fase 3: Carrito de Compras
- **HU014**: Como cliente, quiero agregar productos a mi carrito para prepararme para realizar una compra.
- **HU015**: Como cliente, quiero eliminar productos de mi carrito para ajustar mi pedido.
- **HU016**: Como cliente, quiero actualizar las cantidades de los productos en mi carrito para adaptarlas a mis necesidades.
- **HU017**: Como cliente, quiero ver un resumen de mi carrito (productos, cantidades, precios y total) para revisar mi pedido antes de proceder al pago.
- **HU018**: Como cliente, quiero vaciar mi carrito por completo si decido no realizar la compra.

# Fase 4: Gestión de Pedidos
- **HU019**: Como cliente, quiero confirmar mi pedido proporcionando mi dirección de envío y método de pago para finalizar la compra.
- **HU020**: Como cliente, quiero recibir un correo de confirmación con los detalles de mi pedido para asegurarme de que fue procesado correctamente.
- **HU021**: Como cliente, quiero ver un historial de mis pedidos anteriores para consultarlos en cualquier momento.
- **HU022**: Como administrador, quiero visualizar una lista de pedidos realizados por los clientes para gestionarlos y preparar los envíos.
- **HU023**: Como administrador, quiero cambiar el estado de un pedido (Pendiente, Enviado, Entregado) para reflejar su progreso.

# Fase 5: Gestión de Envíos
- **HU024**: Como cliente, quiero proporcionar una dirección de envío al realizar mi pedido para que pueda recibir los productos.
- **HU025**: Como cliente, quiero recibir un número de seguimiento del envío para saber el estado de mi pedido.
- **HU026**: Como administrador, quiero registrar la información del envío (número de seguimiento, transportista) en los pedidos para gestionar la logística.
- **HU027**: Como cliente, quiero recibir notificaciones por correo cuando el estado de mi pedido cambie (Enviado, Entregado).

# Fase 6: Métodos de Pago
- **HU028**: Como cliente, quiero poder pagar con tarjeta de crédito o débito para realizar compras de manera sencilla.
- **HU029**: Como cliente, quiero poder guardar métodos de pago en mi perfil para usarlos en futuras compras.
- **HU030**: Como administrador, quiero ver un resumen de los pagos realizados por los clientes para llevar control de las transacciones.

# Orden sugerido de implementación
1. **Seguridad y Usuarios (HU001 - HU007)**: Asegura que los usuarios puedan acceder y gestionar sus cuentas.
2. **Catálogo de Productos (HU008 - HU013)**: Configura el catálogo básico de productos.
3. **Carrito de Compras (HU014 - HU018)**: Implementa el flujo de compra.
4. **Gestión de Pedidos (HU019 - HU023)**: Completa el flujo de compra y seguimiento.
5. **Gestión de Envíos (HU024 - HU027)**: Incluye la logística de los pedidos.
6. **Métodos de Pago (HU028 - HU030)**: Agrega soporte para pagos y transacciones.