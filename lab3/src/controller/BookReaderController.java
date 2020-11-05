package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.text.Position;

import model.SortedListModel;
import textproc.GeneralWordCounter;

public class BookReaderController {
	private SortedListModel<Map.Entry<String, Integer>> listModel;

	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 200, 300));
		listModel = new SortedListModel<>(counter.getWordList());
	}

	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());

		// Maincontent
		JList<Map.Entry<String, Integer>> list = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		pane.add(scrollPane, BorderLayout.CENTER);

		// Search and sort panel
		JPanel searchAndSortPanel = new JPanel();
		searchAndSortPanel.setLayout(new FlowLayout());
		JButton alphabeticSortButton = new JButton("Alphabetic");
		JButton frequencySortButton = new JButton("Frequency");
		JButton searchButton = new JButton("Find");
		JTextField searchField = new JTextField(20);
		
		
		
		searchAndSortPanel.add(alphabeticSortButton);
		searchAndSortPanel.add(frequencySortButton);
		searchAndSortPanel.add(searchField);
		searchAndSortPanel.add(searchButton);

		pane.add(searchAndSortPanel, BorderLayout.PAGE_END);

		searchField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER)
					searchButton.doClick();
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		frame.pack();
		frame.setVisible(true);

		// Listeners
		alphabeticSortButton.addActionListener(e -> listModel.sort((x, y) -> x.getKey().compareTo(y.getKey())));
		frequencySortButton.addActionListener(e -> listModel.sort((x, y) -> -(x.getValue() - y.getValue())));
		searchButton.addActionListener(e -> {

			String searchWord = searchField.getText().toString().toLowerCase().trim();
			int index = find(searchWord, list);

			if (index != -1) {
				list.ensureIndexIsVisible(index);
				list.setSelectedIndex(index);

			} else {
				JOptionPane.showMessageDialog(null, "Word \"" + searchWord + "\" not found");
			}

		});

	}
	
	
	private int find(String word, JList<Map.Entry<String, Integer>> list) {
		
		if(word.isEmpty()) {
			return -1;
		}
		
		int index = 0;
		int oldIndex = index;
		
		while (!word.equals(listModel.getElementAt(index).getKey())) {
			index = list.getNextMatch(word, index + 1, Position.Bias.Forward);

			if (index == -1 || oldIndex > index) {
				return -1;
			}

			oldIndex = index;
		}
		return index;
	}

}
