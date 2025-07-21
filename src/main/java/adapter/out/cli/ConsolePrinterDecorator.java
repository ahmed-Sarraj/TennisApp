package adapter.out.cli;

import adapter.common.Adapter;

import java.util.function.Consumer;

/**
 * Décorateur pour l'affichage des scores en console.
 * <p>
 * Cette classe implémente le pattern Decorator pour enrichir l'affichage des scores dans la console.
 * Elle ajoute un préfixe [SCORE] ==> devant chaque score affiché, tout en déléguant l'affichage réel
 * à un Consumer<String> fourni
 * <p>
 */
@Adapter
public class ConsolePrinterDecorator implements Consumer<String> {
    private final Consumer<String> delegate;

    public ConsolePrinterDecorator(Consumer<String> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void accept(String score) {
        delegate.accept("[SCORE] ==> " + score);

    }
}
