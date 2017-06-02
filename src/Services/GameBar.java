package Services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import control.MastermindFrameControl;

public class GameBar {
	public JMenuBar GameMenubar(MastermindFrameControl MmF) {
		JMenuBar gameBar = new javax.swing.JMenuBar();
		JMenu game = game(MmF);
		JMenu MasterMindMenu = Menu("Mastermind", 'M');

		gameBar.add(game);
		game.add(MasterMindMenu);
		MasterMindMenu.add(addMenuItemEasy(MmF));
		MasterMindMenu.add(addMenuItemNormal(MmF));
		game.add(addMenuItemExit());

		gameBar.add(Menu("Settings", 'S'));

		gameBar.add(score(MmF));

		gameBar.setVisible(true);
		return gameBar;
	}

	private JMenu Menu(String tekst, char hotkey) {
		JMenu _menu = new javax.swing.JMenu();
		_menu.setText(tekst);
		_menu.setMnemonic(hotkey);
		return _menu;
	}

	private JMenu game(MastermindFrameControl MmF) {
		JMenu game = Menu("Game", 'G');
		game.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				MmF.MenuGameClicked();
			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
			}

		});
		return game;
	}

	private JMenu score(MastermindFrameControl MmF) {
		JMenu score = Menu("score", 'C');
		score.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				MmF.MenuScoreClicked();
			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
			}

		});
		return score;
	}

	private JMenuItem addMenuItemEasy(MastermindFrameControl MmF) {
		JMenuItem menuItem = new javax.swing.JMenuItem();
		menuItem.setText("Easy");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0));

		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MmF.MenuItemClicked(e);
			}
		});

		return menuItem;
	}

	private JMenuItem addMenuItemNormal(MastermindFrameControl MmF) {
		JMenuItem _menuItem = new javax.swing.JMenuItem();
		_menuItem.setText("Normal");
		_menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0));

		_menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MmF.MenuItemClicked(e);
			}
		});

		return _menuItem;
	}

	private JMenuItem addMenuItemExit() {
		JMenuItem menuItem = new javax.swing.JMenuItem();
		menuItem.setText("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 1));
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit performed");
				// Game.this.dispose();
				System.exit(0);
			}
		});
		return menuItem;
	}
}
