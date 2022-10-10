#include <iostream>
#include<GL/glut.h>
using namespace std;
int gx = 50;
int gy = 100;
void onDisplay() {

    //设置清屏函数
    glClearColor(1, 1, 1, 1);
    glClear(GL_COLOR_BUFFER_BIT);
    /*
        //线段颜色
        glColor3f(1, 0, 0);
        //点模式绘图
        glBegin(GL_LINES);
        glVertex2d(0, 0);
        glVertex2d(100, 100);
    */
    int i;
    char text[] = "hello world";
    //定位输出位置
    glRasterPos2d(gx, gy);
    //设置字符颜色
    glColor3f(1, 0, 0);
    for (i = 0; text[i] != '\0'; i++)
    {
        glutBitmapCharacter(GLUT_BITMAP_8_BY_13, text[i]);
    }

    glEnd();
    //交换前后台缓存
    glutSwapBuffers();
    cout << "display";
}

void onMouse(int button, int state, int x, int y) {
    if (button == GLUT_LEFT && state == GLUT_DOWN) {
        gx = x;
        gy = y;
        glutPostRedisplay();
    }
}

void onReshape(int w, int h) {
    //设置视窗大小 充满窗口
    glViewport(0, 0, w, h);
    //切换矩阵模式到投影矩阵
    glMatrixMode(GL_PROJECTION);
    //载入单位矩阵
    glLoadIdentity();
    //投影
    gluOrtho2D(0, w, h, 0);
    glMatrixMode(GL_MODELVIEW);
    //发送重绘
    glutPostRedisplay();
}
int main(int argc, char* argv[])
{
    glutInit(&argc, argv);
    //设置显示模式
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    //尺寸 位置
    glutInitWindowSize(400, 300);
    glutInitWindowPosition(100, 100);
    //窗口标题
    glutCreateWindow("Hello");
    //给他一个函数指针
    glutDisplayFunc(onDisplay);
    glutReshapeFunc(onReshape);
    glutMouseFunc(onMouse);
    glutMainLoop();
    return 0;
}
