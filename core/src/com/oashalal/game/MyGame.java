package com.oashalal.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Stage stage;
	OrthographicCamera camera;
	float x = 300;
	float y = 200;
	
	@Override
	public void create () {
	    
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		//camera = new OrthographicCamera();
        //camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		/*Button button = new TextButton("Вернуть",mySkin,"small");
		button.setSize(180,60);
		button.setPosition(100, 50);
		button.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			    MyGame.this.x = 300;
				MyGame.this.y = 200;
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				MyGame.this.x = 300;
				MyGame.this.y = 200;
				return true;
			}
		});
		stage.addActor(button);*/
	}

	@Override
	public void render () {
	    
		ScreenUtils.clear(1, 1, 1, 1);
		
		//camera.update();
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        //batch.setProjectionMatrix(camera.combined);
        
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            //camera.unproject(touchPos);
            x = touchPos.x;
            y = touchPos.y;
        }

		batch.begin();
		batch.draw(img, x, y);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {
	    stage.dispose();
		batch.dispose();
		img.dispose();
	}
	
	@Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
