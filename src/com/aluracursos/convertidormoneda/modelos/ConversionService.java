package com.aluracursos.convertidormoneda.modelos;

public class ConversionService {
    // Inyección de dependencia del servicio de API
    private ApiService apiService;

    // Constructor para inicializar el servicio de API
    public ConversionService(ApiService apiService) {
        this.apiService = apiService;
    }

    // Método para realizar la conversión de moneda
    public void realizarConversion(String opcion, double cantidad, ApiResponse apiResponse) {
        String monedaOrigen, monedaDestino;

        // Seleccionar la opción correspondiente
        switch (opcion) {
            case "1":
                monedaOrigen = "USD"; monedaDestino = "ARS"; break;
            case "2":
                monedaOrigen = "ARS"; monedaDestino = "USD"; break;
            case "3":
                monedaOrigen = "USD"; monedaDestino = "BRL"; break;
            case "4":
                monedaOrigen = "BRL"; monedaDestino = "USD"; break;
            case "5":
                monedaOrigen = "USD"; monedaDestino = "COP"; break;
            case "6":
                monedaOrigen = "COP"; monedaDestino = "USD"; break;
            case "7":
                monedaOrigen = "USD"; monedaDestino = "BOB"; break;
            default:
                System.out.println("Opción no válida");
                return;
        }

        // Calcular la conversión utilizando las tasas de la API
        double resultado = calcularConversion(monedaOrigen, monedaDestino, cantidad, apiResponse);
        if (resultado != -1) {
            System.out.printf("%.2f %s = %.2f %s%n", cantidad, monedaOrigen, resultado, monedaDestino);
        }
    }

    // Método para calcular la conversión de moneda
    private double calcularConversion(String monedaOrigen, String monedaDestino, double cantidad, ApiResponse apiResponse) {
        try {
            double tasaConversion;
            if (monedaOrigen.equals("USD")) {
                // Obtener la tasa de conversión de USD a otra moneda
                tasaConversion = apiResponse.getConversionRates().get(monedaDestino);
                return cantidad * tasaConversion;
            } else {
                // Invertir la tasa si la moneda origen no es USD
                tasaConversion = 1 / apiResponse.getConversionRates().get(monedaOrigen);
                return cantidad * tasaConversion;
            }
        } catch (NullPointerException e) {
            System.out.println("Error: Tasa de conversión no disponible para " + monedaOrigen + " o " + monedaDestino);
            return -1;
        }
    }
}
