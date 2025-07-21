package adapter.in.cli;

import adapter.common.Adapter;
import domain.port.TennisGamePort;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Adaptateur CLI pour exécuter une partie de tennis en mode console.
 * <p>
 * Cette classe interagit avec l'utilisateur via la console, récupère la chaîne de jeu,
 * identifie les joueurs, et délègue l'exécution du match au port TennisGamePort.
 * Elle affiche l'évolution du score après chaque point en appellan le output adapter le decorator.
 */

@Adapter
public class ConsoleTennisGameRunner {
    private final TennisGamePort gamePort;
    private final Consumer<String> printer;

    public ConsoleTennisGameRunner(TennisGamePort gamePort, Consumer<String> printer) {
        this.gamePort = gamePort;
        this.printer = printer;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Entrez la chaine de jeu (ex :ABABAA): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.isEmpty()) {
                System.out.println("Aucune chaine de jeu fournie.");
                return;
            }

            Set<Character> distinctChars = input.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toSet());

            char char1;
            char char2;
            switch (distinctChars.size()) {

                case 1: {
                    char1 = distinctChars.iterator().next();
                    char2 = (char1 == 'A') ? 'B' : 'A';
                    System.out.println("Un seul joueur" + char1 + " detecté, l'autre joueur sera " + char2);
                    break;
                }
                case 2: {
                    Iterator<Character> iterator = distinctChars.iterator();
                    char1 = iterator.next();
                    char2 = iterator.next();
                    break;
                }
                default: {
                    System.out.println("Erreur dans le nombre de lettres distinctes.");
                    continue;
                }
            }


            List<String> scores = gamePort.play(input, char1, char2);
            scores.forEach(printer);

        }

    }
}

