package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        //get collision box corner coordinates
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case"up":{
                //predict the position of entity based on speed
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisonOn = true;
                }
                break;
            }

            case"down":{
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisonOn = true;
                }
                break;
            }

            case"left":{
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisonOn = true;
                }
                break;
            }

            case"right":{
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisonOn = true;
                }
                break;
            }
        }

    }
    //check collision for object
    public int checkObject(Entity entity, boolean player){

        int index = 999;//create variable to check if player hit any obj. If yes, return obj index.

        for (int i = 0; i < gp.obj.length; i++){

            if (gp.obj[i] != null){

                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case"up":{
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            //check if two solid area interact with each others
                            if (gp.obj[i].collision == true){
                                entity.collisonOn = true;
                            }
                            //check if it's the player that's interacting with the object
                            if (player == true){
                                index = i;
                            }

                        }
                        break;
                    }

                    case"down":{
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            //check if two solid area interact with each others
                            if (gp.obj[i].collision == true){
                                entity.collisonOn = true;
                            }
                            //check if it's the player that's interacting with the object
                            if (player == true){
                                index = i;
                            }

                        }
                        break;
                    }

                    case"left":{
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            //check if two solid area interact with each others
                            if (gp.obj[i].collision == true){
                                entity.collisonOn = true;
                            }
                            //check if it's the player that's interacting with the object
                            if (player == true){
                                index = i;
                            }

                        }
                        break;
                    }

                    case"right":{
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            //check if two solid area interact with each others
                            if (gp.obj[i].collision == true){
                                entity.collisonOn = true;
                            }
                            //check if it's the player that's interacting with the object
                            if (player == true){
                                index = i;
                            }

                        }
                        break;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        }

        return index;
    }
}
/*
Collision detection idea:

    -Detect coordinates of 4 corners of the hit box
    -Get rol/col number of the the corner.
    -Use switch case to handle each direction. For each direction, check if
    the two corners facing that direction is collision with existing tile.
    If yes, collision is on hence prevent entity from moving.

*/