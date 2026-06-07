package EWalletSystem;

import EWalletSystem.services.Impl.ApplicationServiceImpl;

public class Main {
    public static void main(String[] args) {
        new ApplicationServiceImpl().startApplication();
    }
}