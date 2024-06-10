package com.juanje.ecommerce.backend.infrastructure.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//esta clase define el contexto con mis credenciales en la clase que es una configuración
@Service //podemos crear un bean manualmente en mi config o con esta anotación de spring que me permite crear uno con ella
public class PaypalService {

    private final APIContext apiContext;

    public PaypalService(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    // Este método crea un nuevo pago utilizando la API de PayPal
    public Payment createPayment(
            Double total, // El monto total del pago
            String currency, // La moneda en la que se realizará el pago
            String method, // El método de pago (por ejemplo, PayPal)
            String intent, // La intención del pago (por ejemplo, "venta")
            String description, // Una descripción del pago
            String cancelUrl, // La URL a la que se redirige si el usuario cancela el pago
            String successUrl // La URL a la que se redirige si el usuario completa el pago correctamente
    ) throws PayPalRESTException {

        // Crea un objeto 'Amount' que representa el monto del pago
        Amount amount = new Amount();
        amount.setCurrency(currency); // Establece la moneda del pago
        // Establece el total del pago formateado correctamente
        amount.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f", total));

        // Crea un objeto 'Transaction' que representa la transacción del pago
        Transaction transaction = new Transaction();
        transaction.setDescription(description); // Establece la descripción de la transacción
        transaction.setAmount(amount); // Establece el monto de la transacción

        // Crea una lista de transacciones y agrega la transacción creada
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        // Crea un objeto 'Payer' que representa al pagador
        Payer payer = new Payer();
        payer.setPaymentMethod(method); // Establece el método de pago del pagador

        // Crea un objeto 'Payment' que representa el pago a realizar
        Payment payment = new Payment();
        payment.setIntent(intent); // Establece la intención del pago
        payment.setPayer(payer); // Establece el pagador del pago
        payment.setTransactions(transactions); // Establece las transacciones del pago

        // Crea un objeto 'RedirectUrls' que representa las URLs de redirección después de realizar el pago
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl(successUrl); // Establece la URL de retorno para un pago exitoso
        redirectUrls.setCancelUrl(cancelUrl); // Establece la URL de cancelación para un pago cancelado
        payment.setRedirectUrls(redirectUrls);

        // Realiza la solicitud de creación del pago utilizando el contexto de la API proporcionado
        return payment.create(apiContext); // Retorna el objeto 'Payment' creado
    }

    public Payment executePayment(
            String paymentId, // ID del pago que se va a ejecutar
            String payerId // ID del pagador que autoriza el pago
    ) throws PayPalRESTException {
        // Creo un nuevo objeto 'Payment' para representar el pago a ejecutar
        Payment payment = new Payment();
        // Establezco el ID del pago en el objeto 'Payment'
        payment.setId(paymentId);

        // Creo un nuevo objeto 'PaymentExecution' para ejecutar el pago
        PaymentExecution paymentExecution = new PaymentExecution();
        // Establezco el ID del pagador en el objeto 'PaymentExecution'
        paymentExecution.setPayerId(payerId);

        // Ejecuto el pago utilizando el objeto 'Payment' y 'PaymentExecution'
        // Devuelvo el resultado de la ejecución del pago
        return  payment.execute(apiContext, paymentExecution);
    }



}
