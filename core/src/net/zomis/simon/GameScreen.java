package net.zomis.simon;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import java.util.LinkedList;

/**
 * Created by Zomis on 2014-12-16.
 */
public class GameScreen implements Screen {
    private final SimonGame game;
    private final Button[] images;
    private final LinkedList<Integer> sequence = new LinkedList<Integer>(); // GWT does not support 'Deque' interface
    private final Table table = new Table();
    private final SequenceGenerator generator;
    private final int numButtons;
    private static final float fadeTime = 0.7f;

    public GameScreen(SimonGame game, int numButtons, SequenceGenerator generator) {
        this.game = game;
        this.generator = generator;
        this.numButtons = numButtons;
        this.images = new Button[numButtons];
        table.setFillParent(true);
        for (int i = 0; i < images.length; i++) {
            final int index = i;
            images[i] = new TextButton("", game.getSkin());
            images[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    buttonClick(index);
                }
            });
            table.add(images[i]).expand().fill();
            if (i % 2 == 1) {
                table.row();
            }
        }
        generateSequence();
    }

    private void generateSequence() {
        sequence.clear();
        generator.generate(numButtons, sequence);
        showSequence();
    }

    private void showSequence() {
        setButtonsEnabled(false);
        showSequence(0);
    }

    private void setButtonsEnabled(boolean enabled) {
        for (Button image : images) {
            image.setTouchable(enabled ? Touchable.enabled : Touchable.disabled);
        }
    }

    private void showSequence(final int index) {
        if (sequence.size() <= index) {
            setButtonsEnabled(true);
            return;
        }
        Integer i = sequence.get(index);
        Actor actor = images[i];
        actor.addAction(sequence(alpha(0.7f, fadeTime), alpha(1.0f, fadeTime), run(new Runnable() {
            @Override
            public void run() {
                showSequence(index + 1);
            }
        })));
    }

    private void buttonClick(int index) {
        if (sequence.getFirst() == index) {
            sequence.removeFirst();
            if (sequence.isEmpty()) {
                generateSequence();
            }
        }
        else {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        game.getStage().addActor(table);
    }

    @Override
    public void hide() {
        table.remove();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
