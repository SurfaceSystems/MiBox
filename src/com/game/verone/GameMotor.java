package com.game.verone;

import com.sun.istack.internal.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;


class GameMotor extends JFrame {

    private static int x = 0, y = 0, item = 0;
    private static String chatText = "<html>CLIENT> Game MiBox x_1.0 Started<br></html>";

    private ImageIcon getImageIconFromURL(@NotNull String url) throws IOException {
        URL urlconnection = new URL(url);
        BufferedImage c = ImageIO.read(urlconnection);
        ImageIcon image = new ImageIcon(c);
        return image;
    }




    GameMotor() throws IOException {
        JFrame window = new JFrame();
        window.setTitle("MiBox");
        JPanel bg = new JPanel();
        JLabel info = new JLabel("X: 0 Y: 0  Item: 0 mibox:null");
        JLabel person = new JLabel();
        JLabel cursor = new JLabel();
        cursor.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/cursor.png"));
        cursor.setBounds(0,0,40,40);

        try {
            person.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/person.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        person.setBounds(x,y,40,40);

        info.setVisible(false);
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 38) {
                    y -= 10;
                    person.setBounds(x,y,40,40);
                } else if(e.getKeyCode() == 37) {
                    x -= 10;
                    person.setBounds(x,y,40,40);
                } else if(e.getKeyCode() == 40) {
                    y += 10;
                    person.setBounds(x,y,40,40);
                } else if(e.getKeyCode() == 39) {
                    x += 10;
                    person.setBounds(x,y,40,40);
                } else if(e.getKeyCode() == 114) {
                    info.setBounds(0,0,300,100);
                    info.setHorizontalTextPosition(SwingConstants.LEFT);
                    info.setVerticalAlignment(SwingConstants.TOP);
                    if(info.isVisible()) {
                        info.setVisible(false);
                    } else {
                        info.setVisible(true);
                    }
                }
                if(item == 0) {
                    info.setText("X:" + x + " Y:" + y + "  Item: " + item + "  mibox:null");
                } else if(item == 2) {
                    info.setText("X:" + x + " Y:" + y + "  Item: " + item + "  mibox:dialogo");
                } else if(item == 1) {
                    info.setText("X:" + x + " Y:" + y + "  Item: " + item + "  mibox:sword");
                }
                if(e.getKeyChar() == 't') {
                    JFrame chat = new JFrame();
                    JPanel chatbg = new JPanel();
                    JLabel chattext = new JLabel(chatText);
                    JTextField chatinput = new JTextField();
                    JButton chatsend = new JButton("Send");

                    chattext.setBounds(0,25,300,300-25);
                    chattext.setVerticalTextPosition(SwingConstants.TOP);
                    chattext.setHorizontalTextPosition(SwingConstants.LEFT);
                    chattext.setVerticalAlignment(SwingConstants.TOP);
                    chattext.setHorizontalAlignment(SwingConstants.LEFT);
                    chattext.setForeground(new Color(191, 191, 191));

                    chatsend.setBounds(0,0,65,25);
                    chatinput.setBounds(65,0,300-65,25);
                    chatsend.setHorizontalAlignment(SwingConstants.LEFT);

                    chatsend.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(!chatinput.getText().substring(0,4).equals("/tp ")) {
                                chatText = chatText.substring(0, chatText.length() - 7) + "Player1> " + chatinput.getText() + "<br></html>";
                                chatinput.setText("");
                                chattext.setText(chatText);
                            } else {
                                int cordY = Integer.valueOf(chatinput.getText().substring(4,chatinput.getText().lastIndexOf(" "))), cordX = Integer.valueOf(chatinput.getText().substring(chatinput.getText().lastIndexOf(" ") + 1));
                                person.setBounds(cordX,cordY,40,40);
                                x = cordX;
                                y = cordY;
                                if(item == 0) {
                                    info.setText("X:" + x + " Y:" + y + "  Item: " + item + "  mibox:null");
                                } else if(item == 2) {
                                    info.setText("X:" + x + " Y:" + y + "  Item: " + item + "  mibox:dialogo");
                                } else if(item == 1) {
                                    info.setText("X:" + x + " Y:" + y + "  Item: " + item + "  mibox:sword");
                                }
                                chatinput.setText("");
                                chatText = chatText.substring(0, chatText.length() - 7) + "Teleport player to x: " + x + " and y:" + y + "<br></html>";
                                chattext.setText(chatText);
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });

                    chatbg.setBackground(new Color(34,34,34));
                    chat.add(chattext);
                    chat.add(chatinput);
                    chat.add(chatsend);
                    chat.add(chatbg);

                    chat.setBounds(250,250,300,300);
                    chat.setTitle("MiBox - Chat");
                    chat.setVisible(true);
                    chat.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                } else if(e.getKeyChar() == 'i') {
                    JOptionPane.showConfirmDialog(null, "MiBox x_1.0 NT v_19.0 Java 1.8\nDedicado al grupo scout 147 moratalaz\nGracias a algunos amigos por ayudarme a \nentender como miercoles no responde alguna tecla.", "Acerca de", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        window.addMouseWheelListener(e -> {
            int ev = e.getWheelRotation(); // -1 up 1 down
            if(ev == -1) {
                item--;
                item = NTcounter.checkLoop(0,2,item);
            } else if(ev == 1) {
                item++;
                item = NTcounter.checkLoop(0,2,item);
            }
            if(item == 0) {
                try {
                    person.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/person.png"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if(item == 1) {
                try {
                    person.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/personstick.png"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if(item == 2) {
                try {
                    person.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/personsword.png"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(item == 0) {
                info.setText("X:" + x + " Y:" + y + "  Item: " + item + "  mibox:null");
            } else if(item == 2) {
                info.setText("X:" + x + " Y:" + y + "  Item: " + item + "  mibox:dialogo");
            } else if(item == 1) {
                info.setText("X:" + x + " Y:" + y + "  Item: " + item + "  mibox:sword");
            }
        });



        Random randomx = new Random();
        int randx = randomx.nextInt(26) * 20;
        Random randomy = new Random();
        int randy = randomy.nextInt(26) * 20;
        JLabel bed = new JLabel();
        bed.setBounds(randx,randy, 40,40);
        try {
            bed.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/bed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }



        window.setBounds(0,0,500,500);
        bg.setBackground(new Color(13, 255, 20));
        bg.setBounds(0,0,20,20);
        try {
            window.setIconImage(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/person.png").getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        window.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(item == 0) {
                    try {
                        person.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/personuse.png"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if(item == 2) {
                    try {
                        person.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/personsworduse.png"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if(item == 1) {
                    try {
                        person.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/personstickuse.png"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(item == 0) {
                try {
                    person.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/person.png"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if(item == 2) {
                try {
                    person.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/personsword.png"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if(item == 1) {
                try {
                    person.setIcon(getImageIconFromURL("https://raw.githubusercontent.com/SurfaceSystems/MiBox/main/personstick.png"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        window.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                cursor.setBounds(e.getX()-10,e.getY()-30,40,40);
            }
        });


        window.add(person);
        window.add(info);
        window.add(bed);
        window.add(cursor);
        window.add(bg);
        window.setCursor( window.getToolkit().createCustomCursor(
                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                new Point(),
                null ) );
        window.setVisible(true);

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
