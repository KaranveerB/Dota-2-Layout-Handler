#ifndef DOTA_2_LAYOUT_HANDLER_FILE_SELECTOR_WIDGET_H
#define DOTA_2_LAYOUT_HANDLER_FILE_SELECTOR_WIDGET_H

#include <QApplication>
#include <QFileDialog>
#include <QHBoxLayout>
#include <QLabel>
#include <QLineEdit>
#include <QPushButton>
#include <QStackedWidget>
#include <QVBoxLayout>
#include <QWidget>

#include "d2_layout_handler.h"
#include "transfer_widget.h"

class FileSelectorWidget  : public QWidget {
Q_OBJECT
private:
	QStackedWidget *parent;
	void switchParentToTransferWidget(D2LayoutHandler* d2LayoutHandler);
public:
	explicit FileSelectorWidget(QStackedWidget *parent);
	~FileSelectorWidget();
private slots:
};


#endif //DOTA_2_LAYOUT_HANDLER_FILE_SELECTOR_WIDGET_H