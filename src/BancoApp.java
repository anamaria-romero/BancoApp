class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

class MontoInvalidoException extends Exception {
    public MontoInvalidoException(String mensaje) {
        super(mensaje);
    }
}

class CuentaBancaria {
    private String titular;
    private double saldo;

    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) throws MontoInvalidoException {
        if (monto <= 0) {
            throw new MontoInvalidoException("El monto a depositar debe ser mayor a cero.");
        }
        saldo += monto;
        System.out.println("Depósito exitoso. Saldo actual: " + saldo);
    }

    public void retirar(double monto) throws SaldoInsuficienteException, MontoInvalidoException {
        if (monto <= 0) {
            throw new MontoInvalidoException("El monto a retirar debe ser mayor a cero.");
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar la transacción.");
        }
        saldo -= monto;
        System.out.println("Retiro exitoso. Saldo actual: " + saldo);
    }

    public void transferir(CuentaBancaria destino, double monto) throws SaldoInsuficienteException, MontoInvalidoException {
        if (monto <= 0) {
            throw new MontoInvalidoException("El monto a transferir debe ser mayor a cero.");
        }
        this.retirar(monto);
        destino.depositar(monto);
        System.out.println("Transferencia exitosa. Nuevo saldo: " + this.saldo);
    }
}

public class BancoApp {
    public static void main(String[] args) {
        try {
            CuentaBancaria cuenta1 = new CuentaBancaria("Juan Perez", 5000);
            CuentaBancaria cuenta2 = new CuentaBancaria("Ana Garcia", 3000);

            System.out.println("Saldo inicial de Juan: " + cuenta1.getSaldo());
            System.out.println("Saldo inicial de Ana: " + cuenta2.getSaldo());

            cuenta1.depositar(1000);

            cuenta2.retirar(500);

            cuenta1.transferir(cuenta2, 2000);

        } catch (SaldoInsuficienteException | MontoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

