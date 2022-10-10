/*******************************************************************************
 * 图形学教学程序：扫描线转换填充 
 * 版权声明：您可以随意使用、复制或传播本代码，但请保留原作者姓名
 * 作者：成都理工大学信息工程学院-邓飞  2010.8
*******************************************************************************/
#include <gl/glut.h>
#include <stdio.h>
#include <windows.h>
#include "tools.h"
#include "gpoint2.h"
#include "gpolygon2.h"

#include "gpolygon2.h" 

// 样条控制点数组 
GPolygon2i  gPolygon;
// 鼠标左键选中的控制点  
int gSelectedIndex = -1;
// Shift 键是否按下 
bool gIsShiftDown = false;
// 是否已完成多边形填充 
bool gIsFillPolygon = false;


void drawPoints()
{
    for(int i=0; i<gPolygon.count(); i++)
    {
        // 绘制矩形控制点
        glColor3f(0, 0, 1); 
        gltRect2d(gPolygon[i].x()-5, gPolygon[i].y()-5,
                  gPolygon[i].x()+5, gPolygon[i].y()+5);
    }
}

void drawTags()
{
    char text[32];
    for(int i=0; i<gPolygon.count(); i++)
    {
        // 标注坐标 
        sprintf(text, "(%d, %d)", (int)gPolygon[i].x(), (int)gPolygon[i].y());
        glColor3f(1, 0, 0);
        gltRasterText(gPolygon[i].x()+10, gPolygon[i].y()-5, text);
    }
}

void drawCtrlLines()
{
    int n = gPolygon.count();
    for(int i=0; i<n; i++)
    {
        glColor3f(0.6f, 0.6f, 0.6f);
        gltLine2d(gPolygon[i].x(), gPolygon[i].y(), 
                  gPolygon[(i+1)%n].x(), gPolygon[(i+1)%n].y());
    }
}

// 绘制事件函数 
void onDisplay()
{
    glClearColor(1, 1, 1, 0);
    glClear(GL_COLOR_BUFFER_BIT);

    // 填充多边形 
    glColor3f(1, 1, 0);
    if (gIsFillPolygon) gltFillPolygon(gPolygon, gPolygon.count());
    // 绘制控制点 
    drawPoints();
    // 绘制多边形外框线
    drawCtrlLines();
    // 绘制顶点标注 
    drawTags();

    glutSwapBuffers();  // 交换缓存  
}


// 窗体尺寸变化事件 
void onReshape(int w, int h)
{
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    
    gluOrtho2D(0, w, h, 0);
    glMatrixMode(GL_MODELVIEW);
    glutPostRedisplay();
}       

// 鼠标移动事件函数 
void onMotion(int x, int y)
{
    if (gSelectedIndex >= 0)
    {
        gPolygon[gSelectedIndex].set(x, y);
        glutPostRedisplay();
    }
}

// 鼠标事件函数 
void onMouse(int button, int state, int x, int y)
{
    if (button == GLUT_LEFT_BUTTON)
    {
        if (state == GLUT_DOWN)
        {
            if (gIsFillPolygon)
            {
                gPolygon.clear();
                gIsFillPolygon = false;
            }

            int index = gPolygon.findByCoord(x, y);
            if (index < 0)   // 添加控制点 
            {
                index = gPolygon.count();
                gPolygon.addLast(GPoint2i(x, y));
                gSelectedIndex = index;
                gIsFillPolygon = false;
            }
            else            // 修改(删除)已有控制点  
            {
                if (glutGetModifiers() & GLUT_ACTIVE_SHIFT) // 删除 
                {
                    gPolygon.remove(index);
                }
                else
                {
                    gSelectedIndex = index;
                }
                gIsFillPolygon = false;
            }
        }
        else if (state == GLUT_UP)
        {
            if (gSelectedIndex >= 0)
            {
                gPolygon[gSelectedIndex].set(x, y);
                glutPostRedisplay();
                gSelectedIndex = -1;
            }
        }
        glutPostRedisplay();
    }
    else if (button == GLUT_RIGHT_BUTTON)
    {
        gIsFillPolygon = true;
        glutPostRedisplay();
    }
}

int main(int argc, char *argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(400, 300);
	glutInitWindowPosition(100, 100);
	glutCreateWindow("Fill Polygon");
	
	glutReshapeFunc(onReshape);
	glutDisplayFunc(onDisplay);
	
	glutMouseFunc(onMouse);
	glutMotionFunc(onMotion);
	
	glutMainLoop();
	
	return 0;
}
