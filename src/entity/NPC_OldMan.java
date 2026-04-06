package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{
    public NPC_OldMan(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
    }

    public void setDialogue(){
        dialogues[0] = "Hello, boy.";
        dialogues[1] = "So you've come to this island\nto find the treasure ?";
        dialogues[2] = "I used to be a great wizard but now...\nI'm a bit too old for taking an adventure";
        dialogues[3] = "Well, good luck on you.";

    }

    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1; // chon 1 so tu 1 - 100

            if(i <= 25){
                direction = "up";
            }
            else if(i <= 50){
                direction = "down";
            }
            else if(i <= 75){
                direction = "left";
            }
            else{
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }

    public void speak(){
        super.speak();
    }
}
