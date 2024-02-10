package com.oashalal.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stage;
	OrthographicCamera camera;
	int width;
	int height;
	
	@Override
	public void create () {
	    
	    width = Gdx.graphics.getWidth();
	    height = Gdx.graphics.getHeight();
	    
		batch = new SpriteBatch();
		Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        
        stage = new Stage(new ScreenViewport(camera));
		Gdx.input.setInputProcessor(stage);
		
		Table table = new Table();
		table.setSize(width - 100, width - 100);
		table.setPosition(width/2 - table.getWidth()/2, height/2 - table.getHeight()/2);
		stage.addActor(table);
		
		for (int i = 0; i<8; i++){
		    for (int j = 0; j<8 ; j++){
		        TextButton textButton = new TextButton(Integer.toString(i*8 + j), mySkin, "default");
		        textButton.addListener(new InputListener(){ 
		        	@Override
		        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		        	}
        			@Override
        			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        				textButton.setText("0");
        				return true;
        			}
        		});
        		table.add(textButton);
		    }
		    table.row();
		}
	}

	@Override
	public void render () {
	    
		ScreenUtils.clear(1, 1, 1, 1);
		
		camera.update();
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);
        
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {
	    stage.dispose();
		batch.dispose();
	}
	
	@Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
