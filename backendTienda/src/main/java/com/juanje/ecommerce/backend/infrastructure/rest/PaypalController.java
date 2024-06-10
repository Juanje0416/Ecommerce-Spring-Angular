package com.juanje.ecommerce.backend.infrastructure.rest;

import com.juanje.ecommerce.backend.domain.model.DataPayment;
import com.juanje.ecommerce.backend.domain.model.UrlPaypalResponse;
import com.juanje.ecommerce.backend.infrastructure.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RequestMapping("/api/v1/payments")
public class PaypalController {
    private final PaypalService paypalService;
    private final String SUCCESS_URL = "http://localhost:8085/api/v1/payments/success";
    private  final String CANCEL_URL = "http://localhost:8085/api/v1/payments/cancel";

    //etse metodo me conecta a la api de paypal y permite que paypal valide el pago
    @PostMapping
    public UrlPaypalResponse createPayment(@RequestBody DataPayment dataPayment) {
        log.info("dat {}", dataPayment);
        try {
            // Intento crear un nuevo pago utilizando los datos recibidos
               Payment payment = paypalService.createPayment(
                    Double.valueOf(dataPayment.getAmount()), // Obtengo el monto del pago
                    dataPayment.getCurrency(), // Obtengo la moneda del pago
                    dataPayment.getMethod(), // Obtengo el método de pago
                    "sale", // Establezco la intención del pago como "venta"
                    dataPayment.getDescription(), // Obtengo la descripción del pago
                    CANCEL_URL, // Establezco la URL de cancelación
                    SUCCESS_URL // Establezco la URL de éxito
            );
            // Recorro los enlaces del pago para encontrar la URL de aprobación
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    // Devuelvo la URL de aprobación para redirigir al usuario
                    return new UrlPaypalResponse(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            // Si hay una excepción al crear el pago, imprimo la traza de la excepción
            e.printStackTrace();
        }
        // Si no se encuentra la URL de aprobación o hay una excepción, devuelvo una cadena vacía
        return new UrlPaypalResponse("http://localhost:4200");
    }

    @GetMapping("/success")
    public RedirectView paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ) {
        try {
            // Intento ejecutar el pago utilizando el ID del pago y el ID del pagador
            Payment payment = paypalService.executePayment(paymentId, payerId);
            // Si el estado del pago es "aprobado", redirijo al usuario a la URL de éxito
            if (payment.getState().equals("approved")) {
                return new RedirectView("http://localhost:4200/payment/success");
                //return new RedirectView("http://localhost:4200");
            }
        } catch (PayPalRESTException e) {
            // Si hay una excepción al ejecutar el pago, imprimo la traza de la excepción
            e.printStackTrace();
        }
        // Si el pago no está aprobado o hay una excepción, devuelvo nulo
        return new RedirectView("http://localhost:4200");
    }

    @GetMapping("/cancel")
    public RedirectView paymentCancel() {
        // Redirijo al usuario a la página de inicio en caso de cancelación del pago
        return new RedirectView("http://localhost:4200");
    }



}
