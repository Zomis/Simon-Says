package net.zomis.simon;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Zomis on 2014-12-16.
 */
public class MenuScreen implements Screen {

    private static final int NUM_BUTTONS = 4;
    private final SimonGame game;
    private final Table table;

    public MenuScreen(final SimonGame game) {
        this.game = game;
        this.table = new Table();

        table.setFillParent(true);
        addPlayButton("Random", NUM_BUTTONS, new SimpleGenerator(3));
    }

    private void addPlayButton(String text, final int numButtons, final SequenceGenerator generator) {
        TextButton playButton = new TextButton(text, game.getSkin());
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, numButtons, generator));
            }
        });
        table.add(playButton).expand().fill();
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
