public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        System.out.println("\nCreate");
        System.out.println(clientService.create("Abdula"));
        System.out.println("\nGet client by id");
        System.out.println(clientService.getById(6));
        System.out.println("\nPrint all clients");
        for (ClientService.Client res: clientService.listAll()) {
            System.out.println(res.name() + " " + res.id());
        }
        clientService.deleteById(6);
        clientService.setName(5, "not Abdula");
        System.out.println("\nPrint all clients after deletion and updating data");
        for (ClientService.Client res: clientService.listAll()) {
            System.out.println(res.name() + " " + res.id());
        }

    }
}
