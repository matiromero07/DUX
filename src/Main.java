import tournament.Match;
import tournament.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cantPoints = 0;
        int cantPoints2 = 0;
        int cantGame = 0;
        int cantGame2 = 0;
        int cantSet1 = 0;
        int cantSet2 = 0;
        int opcion = 0;
        int cantPointsAD1 = 0;
        int cantPointsAD2 = 0;
        boolean ban1 = false;
        boolean ban = false;
        boolean ban2 = false;
        int cantSet = 0;
        int winProbability1 = 0;
        int winProbability2 = 0;
        int winner = 0;


        System.out.println("Bienvenido! Ingrese el nombre del torneo:");
        String nameTournament = sc.nextLine();

        System.out.println("Ingrese nombre del jugador: ");
        String name1 = sc.nextLine();

        System.out.println("Ingrese la probabilidad de que el jugador 1 gane");

        //Validar que la probabilidad este entre 0 y 100
        while(!ban1) {

            winProbability1 = sc.nextInt();

            if(winProbability1 > 0 && winProbability1 <= 100){
                winProbability2 = 100 - winProbability1;
                ban1 = true;
            }else{
                System.out.println("Ingrese una probabilidad entre 0 y 100%: ");
            }
        }

        System.out.println("Ingrese nombre del jugador 2: ");
        String name2 = sc.next();

        //Validar que no se ingrese una cantidad de set incorrecta
        while (!ban) {
            System.out.println("A cuantos set se jugará? (3 | 5): ");
            cantSet = sc.nextInt();

            switch (cantSet) {
                case 3:
                    cantSet = 3;
                    ban = true;
                    break;

                case 5:
                    cantSet = 5;
                    ban = true;
                    break;

                default:
                    System.out.println("Elija una opción válida: ");
            }

        }

        Player p1 = new Player(name1, winProbability1);
        Player p2 = new Player(name2, winProbability2);
        Match m = new Match(nameTournament, cantSet);

        System.out.println(m.getNameTournament() + "\n" +
                "Player  |  Points  |  Game  |  Set  |\n" +
                p1.getName() + " |  0      |   0    |   0   |\n" +
                p2.getName() + " |  0      |   0    |   0   |\n" +
                "____________________________________________");

        //While para preguntar por la revancha
        while (opcion != 2) {

            cantPoints = 0;
            cantPoints2 = 0;
            cantGame = 0;
            cantGame2 = 0;
            cantSet1 = 0;
            cantSet2 = 0;


            while (cantSet1 < cantSet && cantSet2 < cantSet) {

                int random = (int) Math.floor(Math.random() * 100);

                //Probabilidad de que gane un jugador o otro
                if (random < winProbability1) {
                    winner = 0;
                } else {
                    winner = 1;
                }

                // (Winner = 0: Jugador 1) | (Winner = 1: Jugador 2)
                if (winner == 0) {

                    //Jugador 1

                    //Suma de puntos
                    if (cantPoints == 30) {
                        cantPoints = cantPoints + 10;
                    } else {
                        cantPoints = cantPoints + 15;
                    }

                    System.out.println(m.getNameTournament() + "\n" +
                            "Player  |  Points  |  Game  |  Set  |\n" +
                            p1.getName() + " |  " + cantPoints + "      |   " + cantGame + "    |   " + cantSet1 + "   | -\n" +
                            p2.getName() + " |  " + cantPoints2 + "      |  " + cantGame2 + "   |   " + cantSet2 + "   |\n" +
                            "____________________________________________");


                    //Cuando el game llega a 6 se suma un set y se vuelven a 0 los puntos
                    if (cantGame == 6 && cantPoints == 40) {
                        cantSet1++;
                        cantGame = 0;
                        cantPoints = 0;
                        cantPoints2 = 0;
                    }

                    //Cuando los puntos llegan a 40 se suma un game y se vuelven a 0 los puntos
                    //Si estan 40-40 se va al adicional
                    if (cantPoints == 40) {
                        if (cantPoints2 == 40) {

                            //Se genera nuevamente un aleatorio para ver quien gana el AD
                            winner = (int) Math.floor(Math.random() * 2);

                            //Cuando estan 40 - 40, el que hace 2 puntos seguidos gana el game
                            while (cantPointsAD1 != 2 || cantPointsAD2 != 2) {
                                if (winner == 0) {
                                    cantPointsAD1++;
                                    cantPointsAD2 = 0; // se pone en 0 a los puntos del segundo jugador para corroborar que sean 2 puntos seguidos
                                } else {
                                    cantPointsAD2++;
                                    cantPointsAD1 = 0;
                                }
                            }

                        } else {
                            cantPoints = 0;
                            cantGame++;
                            cantPoints2 = 0;
                        }
                    }

                    //Si se eligió jugar a 3 set
                    if (cantSet == 3) {
                        if (cantSet1 == cantSet - 1) {
                            System.out.println(m.getNameTournament() + "\n" +
                                    "Player  |  Points  |  Game  |  Set  |\n" +
                                    p1.getName() + " |  " + cantPoints + "      |   " + cantGame + "    |   " + cantSet1 + "   |\n" +
                                    p2.getName() + " |  " + cantPoints2 + "      |  " + cantGame2 + "   |   " + cantSet2 + "   |\n" +
                                    "____________________________________________");
                            System.out.println("El jugador: " + p1.getName() + " ganó");
                            break;
                        }
                        //Si se eligió jugar a 5 set
                    } else {
                        if (cantSet1 == cantSet - 2) {
                            System.out.println(m.getNameTournament() + "\n" +
                                    "Player  |  Points  |  Game  |  Set  |\n" +
                                    p1.getName() + " |  " + cantPoints + "      |   " + cantGame + "    |   " + cantSet1 + "   |\n" +
                                    p2.getName() + " |  " + cantPoints2 + "      |  " + cantGame2 + "   |   " + cantSet2 + "   |\n" +
                                    "____________________________________________");
                            System.out.println("El jugador: " + p1.getName() + " ganó");
                            break;
                        }
                    }

                } else {
                    //Jugador 2

                    if (cantPoints2 == 30) {
                        cantPoints2 = cantPoints2 + 10;
                    } else {
                        cantPoints2 = cantPoints2 + 15;
                    }

                    System.out.println(m.getNameTournament() + "\n" +
                            "Player  |  Points  |  Game  |  Set  |\n" +
                            p1.getName() + " |  " + cantPoints + "      |   " + cantGame + "    |   " + cantSet1 + "   |\n" +
                            p2.getName() + " |  " + cantPoints2 + "      |  " + cantGame2 + "   |   " + cantSet2 + "   | -\n" +
                            "____________________________________________");

                    if (cantGame2 == 6 && cantPoints2 == 40) {
                        cantSet2++;
                        cantGame2 = 0;
                        cantPoints = 0;
                        cantPoints2 = 0;
                    }

                    if (cantPoints2 == 40) {
                        if (cantPoints == 40) {
                            winner = (int) Math.floor(Math.random() * 2);

                            while (cantPointsAD1 != 2 || cantPointsAD2 != 2) {
                                if (winner == 0) {
                                    cantPointsAD1++;
                                    cantPointsAD2 = 0;
                                } else {
                                    cantPointsAD2++;
                                    cantPointsAD1 = 0;
                                }
                            }
                        } else {
                            cantPoints2 = 0;
                            cantGame2++;
                            cantPoints = 0;
                        }
                    }

                    if (cantSet == 3) {
                        if (cantSet2 == cantSet - 1) {
                            System.out.println(m.getNameTournament() + "\n" +
                                    "Player  |  Points  |  Game  |  Set  |\n" +
                                    p1.getName() + " |  " + cantPoints + "      |   " + cantGame + "    |   " + cantSet1 + "   |\n" +
                                    p2.getName() + " |  " + cantPoints2 + "      |  " + cantGame2 + "   |   " + cantSet2 + "   |\n" +
                                    "____________________________________________");
                            System.out.println("El jugador: " + p2.getName() + " ganó");
                            break;
                        }
                    } else {
                        if (cantSet2 == cantSet - 2) {
                            System.out.println(m.getNameTournament() + "\n" +
                                    "Player  |  Points  |  Game  |  Set  |\n" +
                                    p1.getName() + " |  " + cantPoints + "      |   " + cantGame + "    |   " + cantSet1 + "   |\n" +
                                    p2.getName() + " |  " + cantPoints2 + "      |  " + cantGame2 + "   |   " + cantSet2 + "   |\n" +
                                    "____________________________________________");
                            System.out.println("El jugador: " + p2.getName() + " ganó");
                            break;
                        }
                    }

                }

            }
            while (!ban2) {
                System.out.println("Desea jugar la revancha?: (1: Si/ 2: No)");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        opcion = 1;
                        ban2 = true;
                        break;

                    case 2:
                        System.out.println("Fin del torneo!");
                        ban2 = true;
                        break;

                    default:
                        System.out.println("Opción incorrecta");

                }
            }
            ban2 = false;
        }

    }
}